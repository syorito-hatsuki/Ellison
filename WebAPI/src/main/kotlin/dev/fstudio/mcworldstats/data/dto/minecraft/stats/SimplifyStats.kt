package dev.fstudio.mcworldstats.data.dto.minecraft.stats


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SimplifyStats(
    @SerialName("stats")
    val stats: Stats
)