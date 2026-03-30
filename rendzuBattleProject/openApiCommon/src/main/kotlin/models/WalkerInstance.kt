package models

data class WalkerInstance (
    var playerId: PlayerId = PlayerId.NONE,
    var dummyId : PlayerDummyId = PlayerDummyId.NONE,
    var state: PlayerState = PlayerState.DEAD,
    var x: Int = 0,
    var y: Int = 0,
    var health: Int = 100,
    var lock: WalkerLock = WalkerLock.NONE,
) {
    fun isEmpty() = this == NONE

    companion object {
        val NONE = WalkerInstance()
    }
}