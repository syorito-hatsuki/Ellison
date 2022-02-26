package dev.fstudio.mc_discord_bot.diskord.command.online

import com.jessecorbett.diskord.api.channel.EmbedField
import com.jessecorbett.diskord.api.common.Message
import com.jessecorbett.diskord.bot.BotContext
import dev.fstudio.mc_discord_bot.api.mcapi.ping.response.ServerPing
import dev.fstudio.mc_discord_bot.di.mcApi
import dev.fstudio.mc_discord_bot.di.mcStatsApi
import dev.fstudio.mc_discord_bot.util.config.ConfigManager.config
import dev.fstudio.mc_discord_bot.util.extensions.fixUnderline
import dev.fstudio.mc_discord_bot.util.extensions.tickToTime
import dev.fstudio.mc_discord_bot.util.onlineRequestError
import dev.fstudio.mc_discord_bot.util.realDaysInDay
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun BotContext.requestOnlinePlayersList(message: Message) {
    if (message.channelId == config.discord.privateServerChannelId) {
        kotlin.runCatching {
            mcApi.getServerPing(
                config.connection.serverIp,
                config.connection.serverPort
            )
        }.onSuccess { data ->
            val additions = requestAdditionalInfoForFields(data)
            message.respond {
                embedOnlinePlayerListMessage(data, additions)
            }
        }.onFailure {
            message.respond(onlineRequestError)
        }

    }
}

suspend fun requestAdditionalInfoForFields(data: ServerPing): MutableList<EmbedField> {
    val playersList = mutableListOf<EmbedField>()
    data.players.sample?.forEach { player ->
        coroutineScope {
            launch {
                runCatching {
                    mcStatsApi.getStats(player.name)
                }.onSuccess {
                    playersList.add(
                        EmbedField(
                            player.name.fixUnderline(),
                            "$realDaysInDay ${it.minecraftPlayOneMinute.tickToTime()}",
                            true
                        )
                    )
                }.onFailure {
                    error(it.stackTraceToString())
                }
            }
        }
    }
    return playersList
}