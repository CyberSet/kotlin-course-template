fun main() {
    val matrixA = Matrix(arrayOf(arrayOf(1.0, -3.0, 1.0), arrayOf(5.0, 4.0, -2.0)))
    val matrixB = Matrix(arrayOf(arrayOf(-7.0, 5.0), arrayOf(2.0, -1.0), arrayOf(4.0, 3.0)))
    val matrixC = Matrix(arrayOf(arrayOf(2.0, -3.0, 1.0), arrayOf(5.0, 4.0, -2.0)))

    //Просмотр значений элемента
    println(matrixA[0,0])

    //Изменение элемента
    matrixA[0,0] = 2.0
    println(matrixA[0,0])

    //Просмотр размерностей матрицы
    println(matrixA.getRowsCount().toString() + " " + matrixA.getColumnCount().toString())

    //Сложение
    var result = matrixA + matrixC
    println(result.toString())

    //Вычитание
    result = matrixA - matrixC
    println(result.toString())

    //Умножение
    result = matrixA * matrixB
    println(result.toString())

    //Модифицирующее сложение
    matrixA += matrixC
    println(matrixA.toString())

    //Модифицирующее вычитание
    matrixA -= matrixC
    println(matrixA.toString())

    //Модифицирующее умножение
    matrixA *= matrixB
    println(matrixA.toString())

    //Умножение на скаляр
    result = matrixA * 2.0
    println(result.toString())

    //Деление на скаляр
    result = matrixA / 2.0
    println(result.toString())

    //Модифицируюшее умножение на скаляр
    matrixA *= 2.0
    println(matrixA.toString())

    //Деление на скаляр
    matrixA /= 2.0
    println(matrixA.toString())

    //Унарный минус
    println((-matrixA).toString())

    //Унарный плюс
    println((+matrixA).toString())

    //Сравнение
    println(matrixA == matrixC)
    val matrixD = Matrix(arrayOf(arrayOf(2.0, -3.0, 1.0), arrayOf(5.0, 4.0, -2.0)))
    println(matrixD == matrixC)
}