import kotlinOtisProj.rendzuBattleProject.api.v1.models.*
import models.*

fun WalkerCtx.toTransport(): IResponse = when (val cmd = command) {
    WalkerCommand.CREATE -> toTransportCreate()
    WalkerCommand.READ -> toTransportRead()
    WalkerCommand.UPDATE -> toTransportUpdate()
    WalkerCommand.DELETE -> toTransportDelete()
    WalkerCommand.NONE -> throw WalkerCommand_unknown(cmd)
}

fun WalkerCtx.toTransportCreate() = PlayerCreateResponse(
    responseType = "create",
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    health = walkerPlayer.health,
    dummyId = walkerPlayer.dummyId.takeIf { it.asString().isNotBlank() }?.toString(),
    position = Position(walkerPlayer.x, walkerPlayer.y),
    state = DummyState.valueOf(walkerPlayer.state.toString()),
)

fun WalkerCtx.toTransportRead() = PlayerReadResponse(
    responseType = "read",
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    playersInRange = null
)

fun WalkerCtx.toTransportUpdate() = PlayerUpdateResponse(
    responseType = "update",
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    playersInRange = walkerSearchedPlayers.toTransportPlayers()
)

fun WalkerCtx.toTransportDelete() = PlayerDeleteResponse(
    responseType = "delete",
    result = state.toResult(),
    errors = errors.toTransportErrors(),
)

private fun List<WalkerInstance>.toTransportPlayers(): List<PlayerCommons> = this
    .map { it.toTransportPlayer() }

private fun WalkerInstance.toTransportPlayer() = PlayerCommons(
    health = health,
    position = Position(x, y),
    dummyId = dummyId.asString(),
    state = DummyState.valueOf(state.toString())
)

private fun List<WalkerError>.toTransportErrors(): List<Error>? = this
    .map { it.toTransportError() }
    .takeIf { it.isNotEmpty() }

private fun WalkerError.toTransportError() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)

private fun WalkerState.toResult(): ResponseResult? = when (this) {
    WalkerState.RUNNING -> ResponseResult.SUCCESS
    WalkerState.FAILING -> ResponseResult.ERROR
    WalkerState.FINISHED -> ResponseResult.SUCCESS
    WalkerState.NONE -> null
}