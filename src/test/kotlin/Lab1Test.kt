import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Lab1Test {

    @Test
    fun testSimpleLeftAlignment() {
        val expected : String = """Умом — Россию не понять,
Аршином общим не измерить.
У ней особенная стать —
В Россию можно только верить."""
        var result : String = alignText("""     Умом — Россию не понять,
Аршином     общим не измерить.
    У   ней особенная стать —                                      
В Россию     можно только верить.                   """, 40, Alignment.LEFT)
        assertEquals(expected, result)
    }

    @Test
    fun testBlankLeftAlignment() {
        val expected : String = ""
        var result : String = alignText("     ")
        assertEquals(expected, result)
    }

    @Test
    fun testWordLongerThanLineLeftAlignment() {
        val expected : String = """
            абра
            када
            бра
        """.trimIndent()
        var result : String = alignText("абракадабра", 4, Alignment.LEFT)
        assertEquals(expected, result)
    }

    @Test
    fun testWordEqualsLineLeftAlignment() {
        val expected : String = """
            Равенство
        """.trimIndent()
        var result : String = alignText("Равенство", 9, Alignment.LEFT)
        assertEquals(expected, result)
    }

    @Test
    fun testLineShiftLeftAlignment() {
        val expected : String = """
            Шла Саша по шоссе и сосала
            сушку
        """.trimIndent()
        var result : String = alignText("Шла Саша по шоссе и сосала сушку", 26, Alignment.LEFT)
        assertEquals(expected, result)
    }
}