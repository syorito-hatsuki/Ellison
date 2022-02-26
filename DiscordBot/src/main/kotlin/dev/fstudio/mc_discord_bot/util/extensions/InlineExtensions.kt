package dev.fstudio.mc_discord_bot.util.extensions

import org.koin.java.KoinJavaComponent

inline fun <reified T> inject() = KoinJavaComponent.inject<T>(T::class.java)
