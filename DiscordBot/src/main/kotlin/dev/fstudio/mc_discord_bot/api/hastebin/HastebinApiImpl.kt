package dev.fstudio.mc_discord_bot.api.hastebin

import io.ktor.client.*
import io.ktor.client.request.*

class HastebinApiImpl(private val httpClient: HttpClient) : HastebinApi {
    override suspend fun uploadText(text: String): Map<String, String> {
        return httpClient.post {
            url("https://www.toptal.com/developers/hastebin/documents")
            body = text
        }
    }
}