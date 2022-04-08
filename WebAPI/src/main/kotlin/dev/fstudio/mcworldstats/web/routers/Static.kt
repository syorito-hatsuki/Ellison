package dev.fstudio.mcworldstats.web.routers

import dev.fstudio.mcworldstats.util.ConfigManager
import io.ktor.http.content.*
import io.ktor.routing.*
import java.io.File

fun Route.routeStatic() {
    static("stats") {
        staticRootFolder = File(ConfigManager.config.minecraftWorlds.playerStatsWorld)
        files("stats")
    }
    static("deaths") {
        staticRootFolder = File(ConfigManager.config.minecraftWorlds.deathCounterWorld)
        files("deaths")
    }
    static("content") {
        staticRootFolder = File("content")
        files("content")
    }
}