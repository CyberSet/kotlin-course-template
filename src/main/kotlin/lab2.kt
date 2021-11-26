import java.util.*
import kotlin.math.pow

enum class Operation(
    val textValue: String,
    val priorityValue: Int,
    val isWaitedToUnary: Boolean,
    val action: (Double, Double) -> Double
) {
    OPENBRACKET("(", 0, true, { _, _ -> 0.0 }),
    CLOSEBRACKET(")", 0, false, { _, _ -> 0.0 }),
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
    var polishNot = ""
    val operationsStack: Stack<Operation> = Stack()
    var tempDigit = ""
    var tempOperation = ""
    var curOperation: Operation
    var isAbleToUnary = true
    var waitingForOperation = false

    for (curChar in expression) {
        if (!curChar.isWhitespace()) {
            if (curChar.isDigit() || curChar == '.') {
                tempDigit = tempDigit.plus(curChar)
                tempOperation = ""

                isAbleToUnary = false
            } else {
                tempOperation = tempOperation.plus(curChar)
                curOperation = getOperation(tempOperation)
                if (tempDigit.isNotBlank()) {
                    polishNot = polishNot.plus("$tempDigit ")
                    waitingForOperation = true
                }
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
                        if (operationsStack.isNotEmpty()) operationsStack.pop()
                        else throw IllegalArgumentException("Operation order is not correct")
                        waitingForOperation = true
                    } else {
                        if(operationsStack.isEmpty() && !waitingForOperation) throw IllegalArgumentException("Operation order is not correct")
                        while (operationsStack.isNotEmpty() && curOperation != Operation.OPENBRACKET && operationsStack.peek().priorityValue >= curOperation.priorityValue) {
                            if(!waitingForOperation) throw IllegalArgumentException("Operation order is not correct")
                            waitingForOperation = false
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
    polishNot = polishNot.plus("$tempDigit ")
    while (operationsStack.isNotEmpty()) {
        if (operationsStack.peek() == Operation.OPENBRACKET || operationsStack.peek() == Operation.CLOSEBRACKET)
            throw IllegalArgumentException("Operation order is not correct")
        polishNot = polishNot.plus(operationsStack.pop().textValue + ' ')
    }
    return polishNot
}

fun calculateReversePolishNot(expression: String): Double {
    val digitStack: Stack<Double> = Stack()
    var tempString = ""
    var curOperation: Operation

    for (curChar in expression) {
        if (curChar.isWhitespace()) {
            if (tempString.toDoubleOrNull() != null) {
                digitStack.push(tempString.toDouble())
            } else {
                curOperation = getOperation(tempString)
                digitStack.push(curOperation.action(digitStack.pop(), digitStack.pop()))
            }
            tempString = ""
        } else
            tempString = tempString.plus(curChar)
    }
    if (digitStack.size != 1) throw IllegalArgumentException("Operation order is not correct")
    return digitStack.peek()
}