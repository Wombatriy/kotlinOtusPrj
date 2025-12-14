import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertNotEquals

class FirstTest {
    @Test
    fun test() {
        assertNotEquals(5, 2 + 2)
        assertEquals(5, 2 + 3)
    }
}