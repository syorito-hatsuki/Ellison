package dev.fstudio.mc_discord_bot

import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.events
import dev.fstudio.mc_discord_bot.di.networkModule
import dev.fstudio.mc_discord_bot.diskord.command.loadClassicCommands
import dev.fstudio.mc_discord_bot.diskord.event.message_create.suggestionChatManagement
import dev.fstudio.mc_discord_bot.diskord.event.message_create.supportChatManagement
import dev.fstudio.mc_discord_bot.diskord.event.ready.requestPlayerOnlineStatus
import dev.fstudio.mc_discord_bot.util.config.ConfigManager.config
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.core.context.startKoin

@DelicateCoroutinesApi
suspend fun main() {
    startKoin {
        modules(networkModule)
    }

    bot(config.discord.botToken) {
        events {
            /*   On Create   */
            requestPlayerOnlineStatus(this@bot)

            /*   On Message Created   */
            suggestionChatManagement()
            supportChatManagement()
        }
        loadClassicCommands()
    }
}
