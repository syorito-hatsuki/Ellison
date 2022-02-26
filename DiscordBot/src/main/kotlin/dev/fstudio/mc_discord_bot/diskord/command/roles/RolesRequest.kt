package dev.fstudio.mc_discord_bot.diskord.command.roles

import com.jessecorbett.diskord.api.channel.MessageEdit
import com.jessecorbett.diskord.api.common.Message
import com.jessecorbett.diskord.bot.BotContext
import com.jessecorbett.diskord.util.words
import dev.fstudio.mc_discord_bot.util.config.ConfigManager.config

suspend fun BotContext.requestRolesList(message: Message) {
    if (message.channelId == config.discord.rolesInfo.rolesChannelId
        || message.channelId == config.discord.adminChannelId
    ) {
        if (message.words.size == 2 && message.words[1] == "update") {
            channel(config.discord.rolesInfo.rolesChannelId).editMessage(
                config.discord.rolesInfo.rolesMessageId,
                MessageEdit("", embed = embedRolesMessage())
            )
        } else {
            message.respond {
                title = embedRolesMessage().title
                description = embedRolesMessage().description
                fields = embedRolesMessage().fields
                color = embedRolesMessage().color
                footer = embedRolesMessage().footer
            }
        }

    }
}
