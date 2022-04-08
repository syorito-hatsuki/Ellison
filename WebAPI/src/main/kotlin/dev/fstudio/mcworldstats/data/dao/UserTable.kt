package dev.fstudio.mcworldstats.data.dao

import org.jetbrains.exposed.sql.Table

    object UserTable : Table("dle_users") {
        val name = varchar("name", 40)
        val uuid = char("uuid", 36, null)
        val abandoned = bool("abandoned")
}