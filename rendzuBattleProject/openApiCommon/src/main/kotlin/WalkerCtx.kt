import kotlinx.datetime.Instant
import models.RequestId
import models.WalkerCommand
import models.WalkerError
import models.WalkerInstance
import models.WalkerSearchContext
import models.WalkerState
import models.WalkerStubs
import models.WalkerWorkMode

private val INSTANT_NONE = Instant.fromEpochMilliseconds(Long.MIN_VALUE)

private val Instant.Companion.MIN
    get() = INSTANT_NONE

data class WalkerCtx(
    var command: WalkerCommand = WalkerCommand.NONE,
    var state: WalkerState = WalkerState.NONE,
    val errors: MutableList<WalkerError> = mutableListOf(),

    var workMode: WalkerWorkMode = WalkerWorkMode.PROD,
    var workStubs: WalkerStubs = WalkerStubs.NONE,

    var requestId: RequestId = RequestId.NONE,
    var timeStart: Instant = Instant.MIN,
    var walkerPlayer: WalkerInstance = WalkerInstance.NONE,
    var walkerSearchContext: WalkerSearchContext = WalkerSearchContext.NONE,

    var walkerSearchedPlayers: MutableList<WalkerInstance> = mutableListOf(),
)