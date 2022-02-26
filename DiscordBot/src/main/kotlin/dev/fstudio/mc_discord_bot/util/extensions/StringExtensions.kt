package dev.fstudio.mc_discord_bot.util.extensions

fun String.fixUnderline(): String = this.replace("_", "\\_")

fun String.convertToDead(isDead: Boolean?): String = if (isDead == true) "~~$this~~" else this
