package dev.fstudio.mc_discord_bot.api.hastebin

interface HastebinApi {
    suspend fun uploadText(
        text: String
    ): Map<String, String>
}