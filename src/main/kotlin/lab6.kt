class ShapeCollector<T : Shape> {
    private val allShapes = mutableListOf<T>()

    fun add(new: T) {
        allShapes.add(new)
    }

    fun addAll(new: MutableList<T>) {
        allShapes.addAll(new)
    }

    fun getAll(): List<T> {
        return allShapes.toList()
    }

    fun getAllSorted(comparator: Comparator<in T>): List<T> {
        return allShapes.sortedWith(comparator)
    }

    fun getAllByClass(shapeClass: Class<out T>): List<T> {
        return allShapes.filter { it::class.java == shapeClass }
    }
}

object ShapeComparator {
    val perimeterComparatorAsc = compareBy<Shape> { it.calcPerimeter() }
    val perimeterComparatorDesc = compareByDescending<Shape> { it.calcPerimeter() }
    val areaComparatorAsc = compareBy<Shape> { it.calcArea() }
    val areaComparatorDesc = compareByDescending<Shape> { it.calcArea() }
    val radiusComparatorAsc = compareBy<Circle> { it.radius }
    val radiusComparatorDesc = compareByDescending<Circle> { it.radius }
}