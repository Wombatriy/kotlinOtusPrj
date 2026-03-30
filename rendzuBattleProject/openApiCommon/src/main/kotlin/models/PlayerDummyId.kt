package models

@JvmInline
value class PlayerDummyId(private val value: String) {
    fun asString() = value

    companion object {
        val NONE = PlayerDummyId("")
    }
}