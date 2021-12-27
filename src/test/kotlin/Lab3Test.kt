

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class Lab3Test {

    @Test
    fun testCircleAreaCalc() {
        val shapeFactory = ShapeFactorImpl()
        val shape = shapeFactory.createCircle(2.0)
        val res = shape.calcArea()
        assertEquals(res, Math.PI * 2.0 * 2.0, 0.01)
    }

    @Test
    fun testCirclePerimeterCalc() {
        val shapeFactory = ShapeFactorImpl()
        val shape = shapeFactory.createCircle(2.0)
        val res = shape.calcPerimeter()
        assertEquals(res, Math.PI * 2.0 * 2.0, 0.01)
    }

    @Test
    fun testCircleError() {
        var result: Exception? = null
        val shapeFactory = ShapeFactorImpl()
        try {
            shapeFactory.createCircle(-2.0)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }


    @Test
    fun testSquareAreaCalc() {
        val shapeFactory = ShapeFactorImpl()
        val shape = shapeFactory.createSquare(2.0)
        val res = shape.calcArea()
        assertEquals(res, 2.0 * 2.0)
    }

    @Test
    fun testSquarePerimeterCalc() {
        val shapeFactory = ShapeFactorImpl()
        val shape = shapeFactory.createSquare(2.0)
        val res = shape.calcPerimeter()
        assertEquals(res, 2.0 * 4)
    }

    @Test
    fun testSquareError() {
        var result: Exception? = null
        val shapeFactory = ShapeFactorImpl()
        try {
            shapeFactory.createSquare(-2.0)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }

    @Test
    fun testRectAreaCalc() {
        val shapeFactory = ShapeFactorImpl()
        val shape = shapeFactory.createRectangle(2.0, 3.0)
        val res = shape.calcArea()
        assertEquals(res, 2.0 * 3.0)
    }

    @Test
    fun testRectPerimeterCalc() {
        val shapeFactory = ShapeFactorImpl()
        val shape = shapeFactory.createRectangle(2.0, 3.0)
        val res = shape.calcPerimeter()
        assertEquals(res, 2.0 + 3.0 + 2.0 + 3.0)
    }

    @Test
    fun testRectError() {
        var result: Exception? = null
        val shapeFactory = ShapeFactorImpl()
        try {
            shapeFactory.createRectangle(-2.0, -3.0)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }

    @Test
    fun testTriangleAreaCalc() {
        val shapeFactory = ShapeFactorImpl()
        val shape = shapeFactory.createTriangle(3.0, 4.0, 5.0)
        val res = shape.calcArea()
        assertEquals(res, (3.0 * 4.0) / 2.0)
    }

    @Test
    fun testTrianglePerimeterCalc() {
        val shapeFactory = ShapeFactorImpl()
        val shape = shapeFactory.createTriangle(3.0, 4.0, 5.0)
        val res = shape.calcPerimeter()
        assertEquals(res, 3.0 + 4.0 + 5.0)
    }

    @Test
    fun testTriangleError() {
        var result: Exception? = null
        val shapeFactory = ShapeFactorImpl()
        try {
            shapeFactory.createTriangle(-2.0, -3.0, -4.0)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }

}