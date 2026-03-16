package models

@JvmInline
value class RequestId(private val value: String) {
    fun asString() = value

    companion object {
        val NONE = RequestId("")
    }
}