package dev.fstudio.mc_discord_bot.diskord.command.rules

import com.jessecorbett.diskord.api.common.Message
import com.jessecorbett.diskord.bot.BotContext
import dev.fstudio.mc_discord_bot.util.config.ConfigManager.config


suspend fun BotContext.requestRules(message: Message) {
    if (message.channelId == config.discord.rulesChannelId) {
        message.respond {
            embedRulesMessage()
        }
    }
}