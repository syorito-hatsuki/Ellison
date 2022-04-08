package dev.fstudio.mcworldstats.web.plugins

import dev.fstudio.mcworldstats.web.routers.*
import dev.fstudio.mcworldstats.web.routers.changelogs.routeChangeLogs
import dev.fstudio.mcworldstats.web.routers.changelogs.routeChangeLogsCss
import io.ktor.application.*
import io.ktor.routing.*

fun Application.configureRouting() {
    routing {
        routeStat()
        routeAllPlayers()
        routeTop()
        routeStatic()
        routeDeathCounter()
        routeChangeLogs()
    }

    routing {
        routeChangeLogsCss()
    }
}
