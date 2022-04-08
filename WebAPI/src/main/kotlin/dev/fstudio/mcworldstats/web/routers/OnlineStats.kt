package dev.fstudio.mcworldstats.web.routers

import dev.fstudio.mcworldstats.data.dao.Player
import dev.fstudio.mcworldstats.data.dao.Players
import dev.fstudio.mcworldstats.data.dao.UserOnlineStatsTable
import io.ktor.routing.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction


fun Route.routeOnlineStats() {
    TODO("Разобратся с работой Kotlin Exposed")
    get {
        transaction {
            SchemaUtils.create(UserOnlineStatsTable)
            UserOnlineStatsTable.insert {
                Players.insert {
                    Player.new {
                        name = "name 1"
                    }
                    Player.new {
                        name = "name 2"
                    }
                    Player.new {
                        name = "name 3"
                    }
                }
            }
        }
    }
}