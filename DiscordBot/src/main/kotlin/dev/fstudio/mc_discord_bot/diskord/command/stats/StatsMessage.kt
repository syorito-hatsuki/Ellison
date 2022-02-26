package dev.fstudio.mc_discord_bot.diskord.command.stats

import com.jessecorbett.diskord.api.channel.Embed
import com.jessecorbett.diskord.api.channel.EmbedFooter
import dev.fstudio.mc_discord_bot.api.mcworldstats.stats.response.Stats
import dev.fstudio.mc_discord_bot.util.*
import dev.fstudio.mc_discord_bot.util.MicsUtil.getAllBlocks
import dev.fstudio.mc_discord_bot.util.MicsUtil.getGroundWalkedDistance
import dev.fstudio.mc_discord_bot.util.MicsUtil.getRandomColor
import dev.fstudio.mc_discord_bot.util.MicsUtil.getSwamDistance
import dev.fstudio.mc_discord_bot.util.extensions.tickToTime
import dev.fstudio.mc_discord_bot.util.extensions.withSuffix

fun Embed.embedPlayerStatsMessage(username: String, data: Stats) {
    title = "$playerStatsTitle - $username"
    description = statsDescription(data)
    color = getRandomColor()
    footer = EmbedFooter(footerText)
}

private fun statsDescription(data: Stats): String = "**$numberOfActions**\n\n" +
        "**$realDaysInDay **${data.minecraftPlayOneMinute.tickToTime()}\n" +
        "**$killsAndDeath **${data.minecraftPlayerKills.withSuffix()} / ${data.minecraftDeaths.withSuffix()}\n" +
        "**$enchantedItems **${data.minecraftEnchantItem.withSuffix()}\n" +
        "**$droppedItems **${data.minecraftDrop.withSuffix()}\n" +
        "**$killedMobs **${data.minecraftMobKills.withSuffix()}\n" +
        "**$jumpCount **${data.minecraftJump.withSuffix()}\n\n" +
        "**$walkedDistance **${getGroundWalkedDistance(data).withSuffix()}\n" +
        "**$swamDistance **${getSwamDistance(data).withSuffix()}\n" +
        "**$flownDistance **${data.minecraftFlyOneCm.withSuffix()}\n\n" +
        "**$totalDistance **${getAllBlocks(data).withSuffix()}"
