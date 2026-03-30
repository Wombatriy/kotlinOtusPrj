package models

@JvmInline
value class PlayerId(private val value: String) {
    fun asString() = value

    companion object {
        val NONE = PlayerId("")
    }
}