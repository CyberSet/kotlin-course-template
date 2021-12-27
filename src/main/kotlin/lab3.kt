
import kotlinx.serialization.Serializable
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt


interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

@Serializable
class Circle(val radius: Double) : Shape {
    init {
        if (radius <= 0)
            throw IllegalArgumentException("Illegal radius")
    }

    override fun toString(): String {
        return "Circle"
    }

    override fun calcArea(): Double {
        return Math.PI * radius.pow(2)
    }

    override fun calcPerimeter(): Double {
        return 2 * Math.PI * radius
    }

}

@Serializable
class Square(val a: Double) : Shape {
    init {
        if (a <= 0)
            throw IllegalArgumentException("Illegal side")
    }

    override fun toString(): String {
        return "Square"
    }

    override fun calcArea(): Double {
        return a.pow(2)
    }

    override fun calcPerimeter(): Double {
        return 4 * a
    }
}

@Serializable
class Rectangle(val a: Double, val b: Double) : Shape {
    init {
        if (a <= 0 || b <= 0)
            throw IllegalArgumentException("Illegal side")
    }

    override fun toString(): String {
        return "Rectangle"
    }

    override fun calcArea(): Double {
        return a * b
    }

    override fun calcPerimeter(): Double {
        return 2 * (a + b)
    }
}

@Serializable
class Triangle(val a: Double, val b: Double, val c: Double) : Shape {
    init {
        if (a + b <= c || a + c <= b || b + c <= a || a <= 0 || b <= 0 || c <= 0)
            throw IllegalArgumentException("Illegal sides")
    }

    override fun toString(): String {
        return "Triangle"
    }

    override fun calcArea(): Double {
        val semiPerimeter: Double = calSemiPerimeter()
        return sqrt(semiPerimeter * (semiPerimeter - a) * (semiPerimeter - b) * (semiPerimeter - c))
    }

    override fun calcPerimeter(): Double {
        return a + b + c
    }

    private fun calSemiPerimeter(): Double {
        return calcPerimeter() / 2
    }
}

interface ShapeFactory {
    fun createCircle(radius: Double): Circle
    fun createSquare(a: Double): Square
    fun createRectangle(a: Double, b: Double): Rectangle
    fun createTriangle(a: Double, b: Double, c: Double): Triangle

    fun createRandomCircle(): Circle
    fun createRandomSquare(): Square
    fun createRandomRectangle(): Rectangle
    fun createRandomTriangle(): Triangle

    fun createRandomShape(): Shape
}

class ShapeFactorImpl : ShapeFactory {
    override fun createCircle(radius: Double): Circle {
        return Circle(radius)
    }

    override fun createSquare(a: Double): Square {
        return Square(a)
    }

    override fun createRectangle(a: Double, b: Double): Rectangle {
        return Rectangle(a, b)
    }

    override fun createTriangle(a: Double, b: Double, c: Double): Triangle {
        return Triangle(a, b, c)
    }

    override fun createRandomCircle(): Circle {
        return Circle(randomDouble())
    }

    override fun createRandomSquare(): Square {
        return Square(randomDouble())
    }

    override fun createRandomRectangle(): Rectangle {
        return Rectangle(randomDouble(), randomDouble())
    }

    override fun createRandomTriangle(): Triangle {
        val a = randomDouble()
        val b = randomDouble()
        val c = randomDouble(abs(b - a).toInt(), (a + b).toInt())
        return Triangle(a, b, c)
    }

    override fun createRandomShape(): Shape {
        return when ((0..3).random()) {
            0 -> createRandomCircle()
            1 -> createRandomSquare()
            2 -> createRandomRectangle()
            3 -> createRandomTriangle()
            else -> throw IllegalStateException("Error")
        }
    }

    private fun randomDouble(a: Int = 10, b: Int = 10000): Double {
        return ((a * 100)..(b * 100)).random().toDouble() / 100
    }
}

fun calcTotalArea(shapes: List<Shape>): Double {
    var totalArea = 0.0
    for (shape in shapes) {
        totalArea += shape.calcArea()
    }
    return totalArea
}

fun calcTotalPerimeter(shapes: List<Shape>): Double {
    var totalPerimeter = 0.0
    for (shape in shapes) {
        totalPerimeter += shape.calcPerimeter()
    }
    return totalPerimeter
}

fun searchMinAreaShape(shapes: List<Shape>): Shape? {
    if (shapes.isEmpty())
        return null
    var minArea = shapes[0].calcArea()
    var minShape = shapes[0]
    for (shape in shapes) {
        if (shape.calcArea() < minArea) {
            minArea = shape.calcArea()
            minShape = shape
        }
    }
    return minShape
}

fun searchMaxAreaShape(shapes: List<Shape>): Shape? {
    if (shapes.isEmpty())
        return null
    var maxArea = shapes[0].calcArea()
    var maxShape = shapes[0]
    for (shape in shapes) {
        if (shape.calcArea() > maxArea) {
            maxArea = shape.calcArea()
            maxShape = shape
        }
    }
    return maxShape
}

fun searchMinPerimeterShape(shapes: List<Shape>): Shape? {
    if (shapes.isEmpty())
        return null
    var minPerimeter = shapes[0].calcPerimeter()
    var minShape = shapes[0]
    for (shape in shapes) {
        if (shape.calcPerimeter() < minPerimeter) {
            minPerimeter = shape.calcPerimeter()
            minShape = shape
        }
    }
    return minShape
}

fun searchMaxPerimeterShape(shapes: List<Shape>): Shape? {
    if (shapes.isEmpty())
        return null
    var maxPerimeter = shapes[0].calcPerimeter()
    var maxShape = shapes[0]
    for (shape in shapes) {
        if (shape.calcPerimeter() > maxPerimeter) {
            maxPerimeter = shape.calcPerimeter()
            maxShape = shape
        }
    }
    return maxShape
}