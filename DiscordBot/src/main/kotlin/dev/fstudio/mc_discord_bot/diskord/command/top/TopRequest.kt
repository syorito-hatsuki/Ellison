package dev.fstudio.mc_discord_bot.diskord.command.top

import com.jessecorbett.diskord.api.common.Message
import com.jessecorbett.diskord.bot.BotContext
import dev.fstudio.mc_discord_bot.di.mcStatsApi
import dev.fstudio.mc_discord_bot.util.config.ConfigManager.config
import dev.fstudio.mc_discord_bot.util.onlineRequestError

suspend fun BotContext.requestPlayersTop(message: Message) {
    if (message.channelId == config.discord.privateServerChannelId) {
        kotlin.runCatching {
            mcStatsApi.getTopPlayers()
        }.onSuccess { data ->
            message.respond {
                embedPlayersTopMessage(data)
            }
        }.onFailure {
            message.respond(onlineRequestError)
        }
    }
}
