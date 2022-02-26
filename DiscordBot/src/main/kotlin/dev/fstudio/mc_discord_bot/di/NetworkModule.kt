package dev.fstudio.mc_discord_bot.di

import dev.fstudio.mc_discord_bot.api.hastebin.HastebinApi
import dev.fstudio.mc_discord_bot.api.hastebin.HastebinApiImpl
import dev.fstudio.mc_discord_bot.api.mcapi.MCApi
import dev.fstudio.mc_discord_bot.api.mcapi.MCApiImpl
import dev.fstudio.mc_discord_bot.api.mcworldstats.MCWorldApi
import dev.fstudio.mc_discord_bot.api.mcworldstats.MCWorldApiImpl
import dev.fstudio.mc_discord_bot.util.config.ConfigManager.config
import dev.fstudio.mc_discord_bot.util.extensions.inject
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import org.koin.dsl.module

val mcApi by inject<MCApi>()
val mcStatsApi by inject<MCWorldApi>()
val hastebinApi by inject<HastebinApi>()

val networkModule = module {
    single { provideKtorClient() }
    single { provideMCApiService(get()) }
    single { provideMCStatsApiService(get()) }
    single { provideHastebinApiService(get()) }
}

private fun provideKtorClient(): HttpClient {
    return HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }
//        install(Logging) {
//            logger = Logger.DEFAULT
//            level = LogLevel.ALL
//        }
        defaultRequest {
            if (url.host == "localhost") {
                url.host = config.webHost
                url.port = config.webPort
            }
            followRedirects = true
        }
    }
}

private fun provideMCApiService(ktorClient: HttpClient): MCApi {
    return MCApiImpl(ktorClient)
}

private fun provideMCStatsApiService(ktorClient: HttpClient): MCWorldApi {
    return MCWorldApiImpl(ktorClient)
}

private fun provideHastebinApiService(ktorClient: HttpClient): HastebinApi {
    return HastebinApiImpl(ktorClient)
}