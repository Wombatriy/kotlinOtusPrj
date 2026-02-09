import kotlin.test.Test
import kotlin.test.assertEquals

class DslResearch {

    @Test
    fun a() {
        class TestA(
            val x: Int = 0,
            val s: String = "asd $x"
        )

        val ints = TestA(51)
        assertEquals("asd 51", ints.s)
    }
}