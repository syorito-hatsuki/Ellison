package dev.fstudio.mc_discord_bot.diskord.event.message_create

import com.jessecorbett.diskord.api.channel.CreateThread
import com.jessecorbett.diskord.api.common.MessageType
import com.jessecorbett.diskord.bot.EventDispatcherWithContext
import dev.fstudio.mc_discord_bot.util.config.ConfigManager
import dev.fstudio.mc_discord_bot.util.extensions.cut
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

fun EventDispatcherWithContext.reportChatManagement() {
    onMessageCreate { message ->
        if (message.channelId == ConfigManager.config.discord.reportChannelId) {
            if (message.type == MessageType.REPLY) {
                channel(message.channelId).deleteMessage(message.id)
            }

            coroutineScope {
                val task = listOf(
                    async {
                        channel(message.channelId).createThreadFromMessage(
                            message.id,
                            CreateThread(message.content.cut(100))
                        )
                    }
                )
                task.awaitAll()
            }
        }
    }
}