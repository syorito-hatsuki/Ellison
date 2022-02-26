package dev.fstudio.mc_discord_bot.util

import com.jessecorbett.diskord.api.common.Color
import com.jessecorbett.diskord.util.Colors
import dev.fstudio.mc_discord_bot.api.mcworldstats.stats.response.Stats
import kotlin.random.Random

object MicsUtil {

    fun getRandomColor(): Color {
        val random = Random(System.currentTimeMillis())
        return Colors.rgb(
            random.nextInt(0, 255), random.nextInt(0, 255), random.nextInt(0, 255)
        )
    }

    fun getGroundWalkedDistance(data: Stats): Int = data.minecraftWalkOneCm +
            data.minecraftSprintOneCm +
            data.minecraftClimbOneCm +
            data.minecraftCrouchOneCm

    fun getSwamDistance(data: Stats): Int = data.minecraftWalkOnWaterOneCm +
            data.minecraftBoatOneCm +
            data.minecraftWalkUnderWaterOneCm +
            data.minecraftSwimOneCm

    fun getAllBlocks(data: Stats): Int = getGroundWalkedDistance(data) +
            getSwamDistance(data) +
            data.minecraftFlyOneCm +
            data.minecraftFlyOneCm
}