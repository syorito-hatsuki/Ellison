package dev.fstudio.mc_discord_bot.diskord.command.list

import com.jessecorbett.diskord.api.common.Message
import com.jessecorbett.diskord.bot.BotContext
import dev.fstudio.mc_discord_bot.di.mcStatsApi
import dev.fstudio.mc_discord_bot.util.config.ConfigManager.config
import dev.fstudio.mc_discord_bot.util.onlineRequestError

suspend fun BotContext.requestPlayerList(message: Message) {
    if (message.channelId == config.discord.privateServerChannelId) {
        kotlin.runCatching {
            mcStatsApi.getAllPlayers()
        }.onSuccess { data ->
            message.respond {
                embedPlayerListMessage(data)
            }
        }.onFailure {
            message.respond(onlineRequestError)
        }
    }
}