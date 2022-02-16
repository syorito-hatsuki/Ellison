package dev.fstudio.mc_discord_bot

import dev.fstudio.mc_discord_bot.di.networkModule
import dev.fstudio.mc_discord_bot.utils.ConfigManager.readConfig
import dev.fstudio.mc_discord_bot.utils.DiskordBotManager
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.core.context.startKoin

@DelicateCoroutinesApi
suspend fun main() {
    startKoin { modules(networkModule) }
    DiskordBotManager.setupBot(readConfig())
}