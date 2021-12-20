fun main() {
    val shapeFactory = ShapeFactorImpl()
    val shapeList: MutableList<Shape> = arrayListOf()
    shapeList.add(shapeFactory.createCircle(4.0))
    shapeList.add(shapeFactory.createRandomCircle())

    shapeList.add(shapeFactory.createSquare(4.0))
    shapeList.add(shapeFactory.createRandomSquare())

    shapeList.add(shapeFactory.createRectangle(4.0, 8.0))
    shapeList.add(shapeFactory.createRandomRectangle())

    shapeList.add(shapeFactory.createTriangle(3.0, 4.0, 5.0))
    shapeList.add(shapeFactory.createRandomTriangle())

    shapeList.add(shapeFactory.createRandomShape())

    println(calcTotalArea(shapeList))
    println(calcTotalPerimeter(shapeList))
    println(searchMaxAreaShape(shapeList)?.toString())
    println(searchMinAreaShape(shapeList)?.toString())
    println(searchMaxPerimeterShape(shapeList)?.toString())
    println(searchMinPerimeterShape(shapeList)?.toString())
}