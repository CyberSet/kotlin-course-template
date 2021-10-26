import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.lang.Exception

internal class Lab1Test {

    @Test
    fun testSimpleLeftAlignment() {
        val expected: String = """Умом — Россию не понять,
Аршином общим не измерить.
У ней особенная стать —
В Россию можно только верить."""
        val result: String = alignText(
            """     Умом — Россию не понять,
Аршином     общим не измерить.
    У   ней особенная стать —                                      
В Россию     можно только верить.                   """, 40
        )
        assertEquals(expected, result)
    }

    @Test
    fun testBlankLeftAlignment() {
        val expected: String = ""
        val result: String = alignText("     ")
        assertEquals(expected, result)
    }

    @Test
    fun testWordLongerThanLineLeftAlignment() {
        val expected: String = """
            абра
            када
            бра
        """.trimIndent()
        val result: String = alignText("абракадабра", 4)
        assertEquals(expected, result)
    }

    @Test
    fun testWordEqualsLineLeftAlignment() {
        val expected: String = """
            Равенство
        """.trimIndent()
        val result: String = alignText("Равенство", 9)
        assertEquals(expected, result)
    }

    @Test
    fun testLineShiftLeftAlignment() {
        val expected: String = """
            Шла Саша по шоссе и сосала
            сушку
        """.trimIndent()
        val result: String = alignText("Шла Саша по шоссе и сосала сушку", 26)
        assertEquals(expected, result)
    }

    @Test
    fun testSmallLineWidthLeftAlignment() {
        var result: Exception? = null
        try {
            alignText("Неважно", 0)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }
}