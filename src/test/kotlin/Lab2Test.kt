import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Lab2Test {

    @Test
    fun testCalculation() {
        assertEquals(calculateReversePolishNot(parseToReversePolishNot("(1 + 2) * 3 - 4 / 5")), 8.2)
    }

    @Test
    fun testUnaryMinus() {
        assertEquals(calculateReversePolishNot(parseToReversePolishNot("-(1 + 2) * 3 - 4 / 5")), -9.8)
    }

    @Test
    fun testUnaryPlus() {
        assertEquals(calculateReversePolishNot(parseToReversePolishNot("+(1 + 2) * 3 - 4 / 5")), 8.2)
    }

    @Test
    fun testBinaryOperationError() {
        var result: Exception? = null
        try {
            calculateReversePolishNot(parseToReversePolishNot("*(1 + 2) * 3 - 4 / 5"))
        } catch (e: Exception) {
            result = e
        }
        Assertions.assertNotNull(result)
    }

    @Test
    fun testExtraOperationError() {
        var result: Exception? = null
        try {
            calculateReversePolishNot(parseToReversePolishNot("(1 + 2) * 3 - 4 /"))
        } catch (e: Exception) {
            result = e
        }
        Assertions.assertNotNull(result)
    }

    @Test
    fun testTwinOperationError() {
        var result: Exception? = null
        try {
            calculateReversePolishNot(parseToReversePolishNot("(1 + 2) * 3 - 4 /* 2"))
        } catch (e: Exception) {
            result = e
        }
        Assertions.assertNotNull(result)
    }

    @Test
    fun testExtraBracketsError() {
        var result: Exception? = null
        try {
            calculateReversePolishNot(parseToReversePolishNot("(1 + 2) * 3 - 4 / 5)"))
        } catch (e: Exception) {
            result = e
        }
        Assertions.assertNotNull(result)
    }

    @Test
    fun testUnsupportedOperationError() {
        var result: Exception? = null
        try {
            calculateReversePolishNot(parseToReversePolishNot("(1 & 2) * 3 - 4 / 5"))
        } catch (e: Exception) {
            result = e
        }
        Assertions.assertNotNull(result)
    }
}