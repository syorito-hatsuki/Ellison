package dev.fstudio.mc_discord_bot.diskord.command.list

import com.jessecorbett.diskord.api.channel.Embed
import com.jessecorbett.diskord.api.channel.EmbedFooter
import dev.fstudio.mc_discord_bot.api.mcworldstats.common.response.Player
import dev.fstudio.mc_discord_bot.util.MicsUtil.getRandomColor
import dev.fstudio.mc_discord_bot.util.allPlayersTitle
import dev.fstudio.mc_discord_bot.util.extensions.convertToDead
import dev.fstudio.mc_discord_bot.util.extensions.fixUnderline
import dev.fstudio.mc_discord_bot.util.footerText

fun Embed.embedPlayerListMessage(data: List<Player>) {
    title = allPlayersTitle
    description = StringBuilder().apply {
        data.forEachIndexed { index, player ->
            append("**${index + 1}. **${player.name.fixUnderline().convertToDead(player.abandoned)}\n")
        }
    }.toString()
    color = getRandomColor()
    footer = EmbedFooter(footerText)
}