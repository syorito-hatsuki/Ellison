package dev.fstudio.mc_discord_bot.model

import dev.fstudio.mc_discord_bot.util.day
import dev.fstudio.mc_discord_bot.util.hour
import dev.fstudio.mc_discord_bot.util.minute

data class Time(
    val days: Int,
    val hours: Int,
    val minutes: Int
) {
    override fun toString(): String {
        return "${days}$day (${hours}$hour : ${minutes}$minute)"
    }
}