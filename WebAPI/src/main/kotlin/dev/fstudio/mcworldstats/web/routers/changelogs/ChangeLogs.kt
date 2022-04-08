package dev.fstudio.mcworldstats.web.routers.changelogs

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*
import java.io.File

fun Route.routeChangeLogs() {

    val directory = File("content${File.separator}changelogs")

    if (!directory.exists()) {
        directory.mkdirs()
    }

    directory.listFiles()?.forEach { file ->
        if (file.name.endsWith(".txt")) {
            get("changelog/${file.name.removeSuffix(".txt")}") {
                if (call.response.status() != null) return@get
                call.respond(HttpStatusCode.OK, String(file.readBytes()))
            }
        }
    }

    get("changelogs") {
        call.response.headers.append(HttpHeaders.CacheControl, "max-age=180")
        call.respondHtml {
            lang = "en"
            head {
                title("Changelogs")
                meta(charset = "utf-8")
                meta("viewport", "width=device-width, initial-scale=1.0")
                link(rel = "stylesheet", href = "changelogs/styles.css", type = "text/css")
            }
            body {
                div("container") {
                    div("gallery") {
                        directory.listFiles()?.forEach {
                            div("gallery-item") {
                                a("../changelog/${it.name.removeSuffix(".txt")}", classes = "gallery-image") {
                                    + it.name
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}