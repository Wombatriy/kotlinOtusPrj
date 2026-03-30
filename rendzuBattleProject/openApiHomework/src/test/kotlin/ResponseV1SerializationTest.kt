import kotlinOtisProj.rendzuBattleProject.api.v1.models.*
import kotlinOtisProj.rendzuBattleProject.openApiHomework.apiV1Mapper
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseV1SerializationTest {
    private val response = PlayerCreateResponse(
        health = 800,
        position = Position(5,100),
        dummyId = "gh468ygj345tb83wt4",
        state = DummyState.ALIVE,
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(response)

        assertContains(json, Regex("\"position\":\\{\"x\":5"))
        assertContains(json, Regex("\"state\":\\s*\"alive\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(response)
        val obj = apiV1Mapper.readValue(json, IResponse::class.java) as PlayerCreateResponse

        assertEquals(response, obj)
    }
}