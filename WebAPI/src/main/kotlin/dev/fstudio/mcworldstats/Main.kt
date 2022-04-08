package dev.fstudio.mcworldstats

import dev.fstudio.mcworldstats.util.ConfigManager.config
import dev.fstudio.mcworldstats.web.plugins.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.css.CssBuilder

fun main() {
    embeddedServer(Netty, host = config.server.host, port = config.server.port, watchPaths = listOf("classes")) {
        install(AutoHeadResponse)
        configureSerialization()
        configureDatabase()
        configureRouting()
        configureCORS()
        configureCachingHeaders()
    }.start(wait = true)
}

suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}