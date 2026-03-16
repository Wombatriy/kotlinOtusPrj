import kotlinOtisProj.rendzuBattleProject.api.v1.models.*
import kotlinOtisProj.rendzuBattleProject.openApiHomework.apiV1Mapper
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestV1SerializationTest {
    private val request = PlayerCreateRequest(
        mode = SimulatorRequestDebugMode.STUB,
        stub = SimulatorRequestDebugStubs.BAD_TITLE,
        playerId = "someStubPlayerId"
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(request)

        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"badTitle\""))
        assertContains(json, Regex("\"playerId\":\\s*\"someStubPlayerId\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(request)
        val obj = apiV1Mapper.readValue(json, IRequest::class.java) as PlayerCreateRequest

        assertEquals(request, obj)
    }

    @Test
    fun deserializeNaked() {
        val jsonString = """
            {"playerId": null}
        """.trimIndent()
        val obj = apiV1Mapper.readValue(jsonString, PlayerCreateRequest::class.java)

        assertEquals(null, obj.playerId)
    }
}