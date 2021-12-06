class Matrix(
    inputValues: Array<Array<Double>>
) {
    private var values: Array<Array<Double>> = emptyArray()
    val rows: Int
        get() = values.size
    val columns: Int
        get() = values[0].size

    init {
        if (inputValues.isEmpty() || inputValues[0].isEmpty()) throw IllegalArgumentException("Incorrect input values")
        for (curLine in inputValues)
            if (curLine.size != inputValues[0].size) throw IllegalArgumentException("Incorrect input values")
        values = Array(inputValues.size) { Array(inputValues[0].size) { 0.0 } }
        for (i in 0 until rows) for (j in 0 until  columns) values[i][j] = inputValues[i][j]
    }

    operator fun plus(other: Matrix): Matrix {
        if (rows != other.rows || columns != other.columns)
            throw IllegalArgumentException("Matrices have different dimensions")
        val result: Array<Array<Double>> = Array(rows) { Array(columns) { 0.0 } }
        for (i in 0 until rows) for (j in 0 until columns) result[i][j] = values[i][j] + other[i, j]
        return Matrix(result)
    }

    operator fun plusAssign(other: Matrix) {
        if (rows != other.rows || columns != other.columns)
            throw IllegalArgumentException("Matrices have different dimensions")
        for (i in 0 until rows) for (j in 0 until columns) values[i][j] = values[i][j] + other[i, j]
    }

    operator fun minus(other: Matrix): Matrix {
        if (rows != other.rows || columns != other.columns)
            throw IllegalArgumentException("Matrices have different dimensions")
        val result: Array<Array<Double>> = Array(rows) { Array(columns) { 0.0 } }
        for (i in 0 until rows) for (j in 0 until columns) result[i][j] = values[i][j] - other[i, j]
        return Matrix(result)
    }

    operator fun minusAssign(other: Matrix) {
        if (rows != other.rows || columns != other.columns)
            throw IllegalArgumentException("Matrices have different dimensions")
        for (i in 0 until rows) for (j in 0 until columns) values[i][j] = values[i][j] - other[i, j]
    }

    operator fun times(other: Matrix): Matrix {
        if (columns != other.rows)
            throw IllegalArgumentException("The dimension of the matrices is not suitable for multiplication")
        val result: Array<Array<Double>> = Array(rows) { Array(other.columns) { 0.0 } }
        var tempDigit: Double
        for (i in result.indices)
            for (j in 0 until result[0].size) {
                tempDigit = 0.0
                for (k in 0 until columns) tempDigit += values[i][k] * other[k, j]
                result[i][j] = tempDigit
            }
        return Matrix(result)
    }

    operator fun timesAssign(other: Matrix) {
        if (columns != other.rows)
            throw IllegalArgumentException("The dimension of the matrices is not suitable for multiplication")
        val result: Array<Array<Double>> = Array(rows) { Array(other.columns) { 0.0 } }
        var tempDigit: Double
        for (i in result.indices)
            for (j in 0 until result[0].size) {
                tempDigit = 0.0
                for (k in 0 until columns) tempDigit += values[i][k] + other[k, j]
                result[i][j] = tempDigit
            }
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
        if (other !is Matrix || rows != other.rows || columns != other.columns)
            return false
        for (i in 0 until rows)
            for (j in 0 until columns)
                if (values[i][j] != other[i, j]) return false
        return true
    }

    override fun hashCode(): Int {
        var result = values.contentDeepHashCode()
        result = 31 * result + values.contentDeepHashCode()
        return result
    }
}