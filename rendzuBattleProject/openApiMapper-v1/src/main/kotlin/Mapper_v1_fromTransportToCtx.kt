import kotlinOtisProj.rendzuBattleProject.api.v1.models.*
import models.*

fun WalkerCtx.fromTransport(request: IRequest) = when (request) {
    is PlayerCreateRequest -> fromTransport(request)
    is PlayerDeleteRequest -> fromTransport(request)
    is PlayerReadRequest -> fromTransport(request)
    is PlayerUpdateRequest -> fromTransport(request)
    else -> UnknownRequestClass(request.javaClass)
}

fun WalkerCtx.fromTransport(request: PlayerCreateRequest) {
    command = WalkerCommand.CREATE
    workMode = request.mode.toWorkMode()
    workStubs = request.stub.toWorkStub()
    request.playerId?.let { walkerPlayer = WalkerInstance(playerId = PlayerId(it)) }
}

fun WalkerCtx.fromTransport(request: PlayerDeleteRequest) {
    command = WalkerCommand.DELETE
    workMode = request.mode.toWorkMode()
    workStubs = request.stub.toWorkStub()
    request.deadDummyId?.let {
        walkerPlayer = WalkerInstance(
            dummyId = if (it.dummyId != null) {
                PlayerDummyId(it.dummyId!!)
            } else {
                PlayerDummyId.NONE
            },
            playerId = if (it.playerId != null) {
                PlayerId(it.playerId!!)
            } else {
                PlayerId.NONE
            },
            state = PlayerState.DEAD
        )
    }
}

fun WalkerCtx.fromTransport(request: PlayerReadRequest) {
    command = WalkerCommand.READ
    workMode = request.mode.toWorkMode()
    workStubs = request.stub.toWorkStub()
    request.radius?.let { walkerSearchContext.radius = it }
    request.x?.let { walkerSearchContext.x = it }
    request.y?.let { walkerSearchContext.y = it }
}

fun WalkerCtx.fromTransport(request: PlayerUpdateRequest) {
    command = WalkerCommand.UPDATE
    workMode = request.mode.toWorkMode()
    workStubs = request.stub.toWorkStub()
    request.newPlayerPosition?.let {
        walkerPlayer = WalkerInstance(
            health = it.health ?: 0,
            x = it.position?.x ?: 0,
            y = it.position?.y ?: 0,
            dummyId = if (it.dummyId != null) {
                PlayerDummyId(it.dummyId!!)
            } else {
                PlayerDummyId.NONE
            },
            state = PlayerState.DEAD
        )
    }
}

private fun SimulatorRequestDebugMode?.toWorkMode(): WalkerWorkMode = when (this) {
    SimulatorRequestDebugMode.PROD -> WalkerWorkMode.PROD
    SimulatorRequestDebugMode.STUB -> WalkerWorkMode.STUB
    SimulatorRequestDebugMode.TEST -> WalkerWorkMode.TEST
    null -> WalkerWorkMode.PROD
}


private fun SimulatorRequestDebugStubs?.toWorkStub(): WalkerStubs = when (this) {
    SimulatorRequestDebugStubs.SUCCESS -> WalkerStubs.SUCCESS
    SimulatorRequestDebugStubs.NOT_FOUND -> WalkerStubs.NOT_FOUND
    SimulatorRequestDebugStubs.BAD_ID -> WalkerStubs.BAD_ID
    SimulatorRequestDebugStubs.BAD_TITLE -> WalkerStubs.BAD_MOVE
    SimulatorRequestDebugStubs.BAD_DESCRIPTION -> WalkerStubs.BAD_MOVE
    SimulatorRequestDebugStubs.BAD_VISIBILITY -> WalkerStubs.BAD_MOVE
    SimulatorRequestDebugStubs.CANNOT_DELETE -> WalkerStubs.CANNOT_DELETE
    SimulatorRequestDebugStubs.BAD_SEARCH_STRING -> WalkerStubs.BAD_MOVE
    null -> WalkerStubs.NONE
}