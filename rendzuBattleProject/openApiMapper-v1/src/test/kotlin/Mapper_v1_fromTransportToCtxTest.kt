import kotlinOtisProj.rendzuBattleProject.api.v1.models.PlayerCreateRequest
import kotlinOtisProj.rendzuBattleProject.api.v1.models.PlayerCreateResponse
import kotlinOtisProj.rendzuBattleProject.api.v1.models.Position
import kotlinOtisProj.rendzuBattleProject.api.v1.models.SimulatorRequestDebugMode
import kotlinOtisProj.rendzuBattleProject.api.v1.models.SimulatorRequestDebugStubs
import models.PlayerId
import models.RequestId
import models.WalkerCommand
import models.WalkerError
import models.WalkerInstance
import models.WalkerState
import models.WalkerStubs
import models.WalkerWorkMode
import kotlin.test.Test
import kotlin.test.assertEquals

class Mapper_v1_fromTransportToCtxTest {
    @Test
    fun fromTransport() {
        val req = PlayerCreateRequest(
            mode = SimulatorRequestDebugMode.STUB,
            stub = SimulatorRequestDebugStubs.SUCCESS,
            playerId = "Odin"
        )

        val context = WalkerCtx()
        context.fromTransport(req)

        assertEquals(WalkerStubs.SUCCESS, context.workStubs)
        assertEquals(WalkerWorkMode.STUB, context.workMode)
        assertEquals(PlayerId("Odin"), context.walkerPlayer.playerId)
    }

    @Test
    fun toTransport() {
        val context = WalkerCtx(
            requestId = RequestId("1234"),
            command = WalkerCommand.CREATE,
            walkerPlayer = WalkerInstance(
                x = 7,
                y = 10
            ),
            errors = mutableListOf(
                WalkerError(
                    code = "err",
                    group = "request",
                    field = "title",
                    message = "wrong title",
                )
            ),
            state = WalkerState.RUNNING,
        )

        val req = context.toTransportCreate() as PlayerCreateResponse

        assertEquals(req.position, Position(7, 10))
        assertEquals(1, req.errors?.size)
        assertEquals("err", req.errors?.firstOrNull()?.code)
        assertEquals("request", req.errors?.firstOrNull()?.group)
        assertEquals("title", req.errors?.firstOrNull()?.field)
        assertEquals("wrong title", req.errors?.firstOrNull()?.message)
    }
}