package dev.fstudio.mc_discord_bot.util.extensions

fun String.fixUnderline(): String = this.replace("_", "\\_")

fun String.convertToDead(isDead: Boolean?): String = if (isDead == true) "~~$this~~" else this

fun String.cut(size: Int): String{
    return if (this.isNotEmpty()) {
        if (this.length > size) this.substring(0, size)
        else this
    } else {
        "Помогите. У меня ошибка"
    }
}