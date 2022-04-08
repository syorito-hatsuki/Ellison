package dev.fstudio.mcworldstats.data.dto.minecraft.stats


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    @SerialName("minecraft:custom")
    val minecraftCustom: MinecraftCustom
)