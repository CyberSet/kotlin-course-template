import java.util.*
import kotlin.math.pow

enum class Operation(
    val textValue: String,
    val priorityValue: Int,
    val isWaitedToUnary: Boolean,
    val action: (Double, Double) -> Double
) {
    OPENBRACKET("(", 0, true, { a, b -> 0.0 }),
    CLOSEBRACKET(")", 0, false, { a, b -> 0.0 }),
    PLUS("+", 1, false, { a, b -> a + b }),
    MINUS("-", 1, false, { a, b -> b - a }),
    MULTIPLY("*", 2, true, { a, b -> a * b }),
    DIVISION("/", 2, true, { a, b -> b / a }),
    POW("^", 3, true, { a, b -> b.pow(a) })
}

fun getOperation(textValue: String): Operation {
    return when (textValue) {
        "(" -> Operation.OPENBRACKET
        ")" -> Operation.CLOSEBRACKET
        "+" -> Operation.PLUS
        "-" -> Operation.MINUS
        "*" -> Operation.MULTIPLY
        "/" -> Operation.DIVISION
        "^" -> Operation.POW
        else -> throw IllegalArgumentException("Unsupported operation")
    }
}

fun parseToReversePolishNot(expression: String): String {
    var polishNot: String = ""
    var operationsStack: Stack<Operation> = Stack()
    var tempDigit: String = ""
    var tempOperation: String = ""
    var curOperation: Operation
    var isAbleToUnary: Boolean = true

    for (curChar in expression) {
        if (!curChar.isWhitespace()) {
            if (curChar.isDigit() || curChar == '.') {
                tempDigit = tempDigit.plus(curChar)
                tempOperation = ""
                isAbleToUnary = false
            } else {
                tempOperation = tempOperation.plus(curChar)
                curOperation = getOperation(tempOperation)
                if (tempDigit.isNotBlank()) polishNot = polishNot.plus(tempDigit + ' ')
                tempDigit = ""
                if (isAbleToUnary && (curOperation == Operation.PLUS || curOperation == Operation.MINUS)) {
                    if (curOperation == Operation.MINUS) polishNot = polishNot.plus("-1 ")
                    else polishNot = polishNot.plus("1 ")
                    operationsStack.push(Operation.MULTIPLY)
                    isAbleToUnary = false
                } else {
                    if (curOperation == Operation.CLOSEBRACKET) {
                        while (operationsStack.isNotEmpty() && operationsStack.peek() != Operation.OPENBRACKET) {
                            polishNot = polishNot.plus(operationsStack.peek().textValue + ' ')
                            operationsStack.pop()
                        }
                        operationsStack.pop()
                    } else {
                        while (operationsStack.isNotEmpty() && curOperation != Operation.OPENBRACKET && operationsStack.peek().priorityValue >= curOperation.priorityValue) {
                            polishNot = polishNot.plus(operationsStack.peek().textValue + ' ')
                            operationsStack.pop()
                        }
                        operationsStack.push((curOperation))
                    }
                    isAbleToUnary = curOperation.isWaitedToUnary
                }
                tempOperation = ""
            }
        }
    }
    polishNot = polishNot.plus(tempDigit + ' ')
    while (operationsStack.isNotEmpty()) {
        polishNot = polishNot.plus(operationsStack.peek().textValue + ' ')
        operationsStack.pop()
    }
    return polishNot
}

fun calculateReversePolishNot(expression: String): Double {
    var digitStack: Stack<Double> = Stack()
    var tempDigit: String = ""
    var tempOperation: String = ""
    var curOperation: Operation

    for (curChar in expression) {
        if (curChar.isWhitespace()) {
            if (tempDigit.isNotBlank()) digitStack.push(tempDigit.toDouble())
            tempDigit = ""
        } else {
            if (curChar.isDigit() || curChar == '.')
                tempDigit = tempDigit.plus(curChar)
            else {
                tempOperation = tempOperation.plus(curChar)
                curOperation = getOperation(tempOperation)
                tempDigit = ""
                digitStack.push(curOperation.action(digitStack.pop(), digitStack.pop()))
            }
            tempOperation = ""
        }
    }
    return digitStack.peek()
}