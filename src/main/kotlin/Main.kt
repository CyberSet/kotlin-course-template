fun main() {
    val shapeFactory = ShapeFactorImpl()
    val shapeCollector = ShapeCollector<Shape>()
    val circleCollector = ShapeCollector<Circle>()
    shapeCollector.add(shapeFactory.createSquare(2.0))
    shapeCollector.add(shapeFactory.createCircle(3.0))
    shapeCollector.add(shapeFactory.createRectangle(4.0, 5.0))
    shapeCollector.add(shapeFactory.createTriangle(6.0, 7.0, 8.0))

    circleCollector.add(shapeFactory.createCircle(2.0))
    circleCollector.add(shapeFactory.createCircle(3.0))
    circleCollector.addAll(listOf( shapeFactory.createCircle(4.0),shapeFactory.createCircle(5.0)))

    println(circleCollector.getAll())

    for (i in shapeCollector.getAllSorted(ShapeComparator.perimeterComparatorAsc))
        println(i.toString() + " " + i.calcPerimeter())

    for (i in shapeCollector.getAllSorted(ShapeComparator.perimeterComparatorDesc))
        println(i.toString() + " " + i.calcPerimeter())

    for (i in shapeCollector.getAllSorted(ShapeComparator.areaComparatorAsc))
        println(i.toString() + " " + i.calcArea())

    for (i in shapeCollector.getAllSorted(ShapeComparator.areaComparatorDesc))
        println(i.toString() + " " + i.calcArea())

    for (i in circleCollector.getAllSorted(ShapeComparator.radiusComparatorAsc))
        println(i.toString() + " " + i.radius)

    for (i in circleCollector.getAllSorted(ShapeComparator.radiusComparatorDesc))
        println(i.toString() + " " + i.radius)

    for (i in shapeCollector.getAllByClass(Rectangle::class.java))
        println(i.toString() + " " + i.calcPerimeter())

    val filePath = "new.json"

    val tools = shapeTool()
    val encodeString = tools.encode(shapeCollector.getAll()[0])

    val decodedShape = tools.decode(encodeString)
    tools.writeToFile(decodedShape.toString(), filePath)
}