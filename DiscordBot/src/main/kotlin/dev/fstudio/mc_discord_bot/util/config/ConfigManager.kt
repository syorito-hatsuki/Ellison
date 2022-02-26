package dev.fstudio.mc_discord_bot.util.config

import dev.fstudio.mc_discord_bot.Build
import net.peanuuutz.tomlkt.Toml
import java.io.File

object ConfigManager {

    private val toml = Toml { ignoreUnknownKeys = true }

    var config: Config
        private set

    private var file = File(
        "config${File.separator}",
        "discord-bot.toml"
    )

    init {
        writeConfig(readConfig())
        config = readConfig()
    }

    private fun writeConfig(oldConfig: Config) {
        file.also {
            val newConfig = toml.encodeToString(
                Config.serializer(),
                oldConfig.copy(version = Build.VERSION)
            )
            it.writeText(newConfig)
        }
    }

    private fun readConfig(): Config {
        if (!file.exists())
            writeConfig(Config())
        return toml.decodeFromString(Config.serializer(), file.readText())
    }
}
