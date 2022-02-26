package dev.fstudio.mc_discord_bot.diskord.command.stats

import com.jessecorbett.diskord.api.common.Message
import com.jessecorbett.diskord.bot.BotContext
import com.jessecorbett.diskord.util.words
import dev.fstudio.mc_discord_bot.di.mcStatsApi
import dev.fstudio.mc_discord_bot.util.config.ConfigManager.config
import dev.fstudio.mc_discord_bot.util.onlineRequestError
import dev.fstudio.mc_discord_bot.util.statsRequestError

suspend fun BotContext.requestPlayerStats(message: Message) {
    if (message.channelId == config.discord.privateServerChannelId) {
        if (message.words.size == 2) {
            val username = message.words[1]
            kotlin.runCatching {
                mcStatsApi.getStats(username)
            }.onSuccess { data ->
                message.respond {
                    embedPlayerStatsMessage(username, data)
                }
            }.onFailure {
                message.respond(onlineRequestError)
            }
        } else message.respond(statsRequestError)
    }
}