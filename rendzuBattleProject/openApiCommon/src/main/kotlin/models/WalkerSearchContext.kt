package models

data class WalkerSearchContext(
    var radius: Int = 0,
    var x: Int = 0,
    var y: Int = 0,
) {
    companion object {
        val NONE = WalkerSearchContext()
    }
}