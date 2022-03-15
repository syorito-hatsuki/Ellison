package dev.fstudio.mc_discord_bot.diskord.event.message_create

import com.jessecorbett.diskord.api.channel.CreateThread
import com.jessecorbett.diskord.api.common.MessageType
import com.jessecorbett.diskord.bot.EventDispatcherWithContext
import dev.fstudio.mc_discord_bot.util.config.ConfigManager
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

//TODO Разобрать на подфайлы и подметоды

fun EventDispatcherWithContext.suggestionChatManagement() {
    onMessageCreate { message ->
        if (message.channelId == ConfigManager.config.discord.suggestionChannelId) {
            if (message.type == MessageType.REPLY) {
                channel(message.channelId).deleteMessage(message.id)
            }

            coroutineScope {
                val task = listOf(
                    async {
                        val threadName: String = if (message.content.length > 100)
                            message.content.substring(0, 100) else message.content

                        channel(message.channelId).createThreadFromMessage(
                            message.id,
                            CreateThread(threadName)
                        )
                    },
                    async {
                        message.react("✅")
                        message.react("❌")
                    }
                )
                task.awaitAll()
            }
        }
    }
}