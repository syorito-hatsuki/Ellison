package dev.fstudio.mc_discord_bot.diskord.event.message_create

import com.jessecorbett.diskord.api.channel.Embed
import com.jessecorbett.diskord.api.channel.EmbedField
import com.jessecorbett.diskord.api.channel.EmbedFooter
import com.jessecorbett.diskord.api.common.User
import com.jessecorbett.diskord.bot.EventDispatcherWithContext
import dev.fstudio.mc_discord_bot.di.hastebinApi
import dev.fstudio.mc_discord_bot.util.MicsUtil.getRandomColor
import dev.fstudio.mc_discord_bot.util.config.ConfigManager.config
import dev.fstudio.mc_discord_bot.util.footerText
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

//TODO Разобрать на подфайлы и подметоды

fun EventDispatcherWithContext.supportChatManagement() {
    onMessageCreate { message ->
        if (message.channelId == config.discord.supportChannelId) {
            if (message.attachments.isNotEmpty()) {
                val embedList = mutableListOf<EmbedField>()
                message.attachments.forEach {
                    if (it.contentType?.contains("text/plain") == true && it.fileName.isLog()) {
                        val response: io.ktor.client.statement.HttpResponse = HttpClient().get(it.url)
                        response.receive<String>().apply {
                            val urlKey = hastebinApi.uploadText(this).values
                            //TODO Локализировать текст
                            embedList.add(
                                EmbedField(
                                    "Название файла: ${it.fileName}",
                                    "https://www.toptal.com/developers/hastebin/${urlKey.first()}",
                                    false
                                )
                            )
                        }
                    } else {
                        //TODO Локализировать текст
                        embedList.add(
                            EmbedField(
                                "Название файла: ${it.fileName}",
                                "Отправленый файл пустой!",
                                false
                            )
                        )
                    }
                    println("===\nContentType: ${it.contentType}\nFilename: ${it.fileName}\nURL: ${it.url}")
                }
                message.delete()
                message.respond(block = msg(message.author, embedList))
            }
        }
    }
}

fun msg(user: User, embedList: MutableList<EmbedField>): Embed.() -> Unit {
    return {
        //TODO Локализировать текст
        title = "Благодарим за отправку лог(а/ов). Мы поможем вам как только будет время"
        description = "Отправитель: <@${user.id}>. "
        fields = embedList
        color = getRandomColor()
        footer = EmbedFooter(footerText)
    }
}

private fun String.isLog(): Boolean {
    return this.endsWith(".txt", true) || this.endsWith(".log", true)
}
