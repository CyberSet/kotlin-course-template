class Matrix(
    inputValues: Array<Array<Double>>
) {
    private var values: Array<Array<Double>> = emptyArray()
    private val rows: Int
    private var columns: Int

    init {
        rows = inputValues.size
        columns = inputValues[0].size
        if (rows == 0 || columns == 0) throw IllegalArgumentException("Incorrect input values")
        for (curLine in inputValues)
            if (curLine.size != columns) throw IllegalArgumentException("Incorrect input values")
        values = inputValues
    }

    operator fun plus(other: Matrix): Matrix {
        if (getRowsCount() != other.getRowsCount() || getColumnCount() != other.getColumnCount())
            throw IllegalArgumentException("Matrices have different dimensions")
        val result: Array<Array<Double>> = Array(rows) { Array(columns) { 0.0 } }
        for (i in 0 until rows) for (j in 0 until columns) result[i][j] = values[i][j] + other[i, j]
        return Matrix(result)
    }

    operator fun plusAssign(other: Matrix) {
        if (getRowsCount() != other.getRowsCount() || getColumnCount() != other.getColumnCount())
            throw IllegalArgumentException("Matrices have different dimensions")
        for (i in 0 until rows) for (j in 0 until columns) values[i][j] = values[i][j] + other[i, j]
    }

    operator fun minus(other: Matrix): Matrix {
        if (getRowsCount() != other.getRowsCount() || getColumnCount() != other.getColumnCount())
            throw IllegalArgumentException("Matrices have different dimensions")
        val result: Array<Array<Double>> = Array(rows) { Array(columns) { 0.0 } }
        for (i in 0 until rows) for (j in 0 until columns) result[i][j] = values[i][j] - other[i, j]
        return Matrix(result)
    }

    operator fun minusAssign(other: Matrix) {
        if (getRowsCount() != other.getRowsCount() || getColumnCount() != other.getColumnCount())
            throw IllegalArgumentException("Matrices have different dimensions")
        for (i in 0 until rows) for (j in 0 until columns) values[i][j] = values[i][j] - other[i, j]
    }

    operator fun times(other: Matrix): Matrix {
        if (getColumnCount() != other.getRowsCount())
            throw IllegalArgumentException("The dimension of the matrices is not suitable for multiplication")
        val result: Array<Array<Double>> = Array(rows) { Array(other.getColumnCount()) { 0.0 } }
        var tempDigit: Double
        for (i in 0 until result.size)
            for (j in 0 until result[0].size) {
                tempDigit = 0.0
                for (k in 0 until columns) tempDigit += values[i][k] * other[k, j]
                result[i][j] = tempDigit
            }
        return Matrix(result)
    }

    operator fun timesAssign(other: Matrix) {
        if (getColumnCount() != other.getRowsCount())
            throw IllegalArgumentException("The dimension of the matrices is not suitable for multiplication")
        val result: Array<Array<Double>> = Array(rows) { Array(other.getColumnCount()) { 0.0 } }
        var tempDigit: Double
        for (i in 0 until result.size)
            for (j in 0 until result[0].size) {
                tempDigit = 0.0
                for (k in 0 until columns) tempDigit += values[i][k] + other[k, j]
                result[i][j] = tempDigit
            }
        columns = other.getColumnCount()
        values = result
    }

    operator fun times(scalar: Double): Matrix {
        val result: Array<Array<Double>> = Array(rows) { Array(columns) { 0.0 } }
        for (i in 0 until rows) for (j in 0 until columns) result[i][j] = values[i][j] * scalar
        return Matrix(result)
    }

    operator fun timesAssign(scalar: Double) {
        for (i in 0 until rows) for (j in 0 until columns) values[i][j] *= scalar
    }

    operator fun div(scalar: Double): Matrix {
        val result: Array<Array<Double>> = Array(rows) { Array(columns) { 0.0 } }
        for (i in 0 until rows) for (j in 0 until columns) result[i][j] = values[i][j] / scalar
        return Matrix(result)
    }

    operator fun divAssign(scalar: Double) {
        for (i in 0 until rows) for (j in 0 until columns) values[i][j] /= scalar
    }

    operator fun get(i: Int, j: Int): Double {
        if (i >= rows || j >= columns || i < 0 || j < 0)
            throw IllegalArgumentException("Indexes out of range")
        return values[i][j]
    }

    operator fun set(i: Int, j: Int, value: Double) {
        if (i >= rows || j >= columns || i < 0 || j < 0)
            throw IllegalArgumentException("Indexes out of range")
        values[i][j] = value
    }

    operator fun unaryMinus(): Matrix {
        val result: Array<Array<Double>> = Array(rows) { Array(columns) { 0.0 } }
        for (i in 0 until rows) for (j in 0 until columns) result[i][j] = -values[i][j]
        return Matrix(result)
    }

    operator fun unaryPlus(): Matrix {
        return this
    }

    fun getRowsCount(): Int {
        return rows
    }

    fun getColumnCount(): Int {
        return columns
    }

    override fun toString(): String {
        var output = ""
        for (i in values.indices) {
            for (j in values[0].indices)
                output += values[i][j].toString() + " "
            output += "\n"
        }
        return output
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Matrix || rows != other.getRowsCount() || columns != other.getColumnCount())
            return false
        for (i in 0 until rows)
            for (j in 0 until columns)
                if (values[i][j] != other[i, j]) return false
        return true
    }
}