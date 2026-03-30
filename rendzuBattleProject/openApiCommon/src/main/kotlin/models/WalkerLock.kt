package models

@JvmInline
value class WalkerLock(private val value: String) {
    fun asString() = value

    companion object {
        val NONE = WalkerLock("")
    }
}