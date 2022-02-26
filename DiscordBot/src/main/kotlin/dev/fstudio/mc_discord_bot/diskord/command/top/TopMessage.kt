package dev.fstudio.mc_discord_bot.diskord.command.top

import com.jessecorbett.diskord.api.channel.Embed
import com.jessecorbett.diskord.api.channel.EmbedField
import com.jessecorbett.diskord.api.channel.EmbedFooter
import dev.fstudio.mc_discord_bot.api.mcworldstats.common.response.Player
import dev.fstudio.mc_discord_bot.util.MicsUtil.getRandomColor
import dev.fstudio.mc_discord_bot.util.extensions.convertToDead
import dev.fstudio.mc_discord_bot.util.extensions.fixUnderline
import dev.fstudio.mc_discord_bot.util.extensions.tickToTime
import dev.fstudio.mc_discord_bot.util.footerText
import dev.fstudio.mc_discord_bot.util.realDaysInDay
import dev.fstudio.mc_discord_bot.util.topPlayersTitle

fun Embed.embedPlayersTopMessage(data: List<Player>) {
    val topTen = mutableListOf<EmbedField>()

    for (i in 0..9) {
        topTen.add(
            EmbedField(
                "${i + 1}. ${data[i].name.fixUnderline().convertToDead(data[i].abandoned)}",
                "$realDaysInDay ${data[i].minecraftPlayOneMinute?.tickToTime()}",
                false
            )
        )
    }

    title = topPlayersTitle
    fields = topTen
    color = getRandomColor()
    footer = EmbedFooter(footerText)
}