import java.util.*

enum class Operation(val textValue: String, val priorityValue: Int) {
    OPENBRACKET("(", 0),
    CLOSEBRACKET(")", 0),
    PLUS("+", 1),
    MINUS("-", 1),
    MULTIPLY("*", 2),
    DIVISION("/", 2),
    POW("^", 3)
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

    for (curChar in expression) {
        if (curChar.isDigit()) {
            tempDigit = tempDigit.plus(curChar)
            tempOperation = ""
        } else {
            tempOperation = tempOperation.plus(curChar)
            curOperation = getOperation(tempOperation)
            if(curOperation.priorityValue != 0) {
                polishNot = polishNot.plus(tempDigit + ' ')
                tempDigit = ""
            }
            if (curOperation == Operation.CLOSEBRACKET) {
                while (operationsStack.isNotEmpty() && operationsStack.peek() != Operation.OPENBRACKET) {
                    polishNot = polishNot.plus(operationsStack.peek().textValue + ' ')
                    operationsStack.pop()
                }
                operationsStack.pop()
            } else {
                while(operationsStack.isNotEmpty() && curOperation != Operation.OPENBRACKET && operationsStack.peek().priorityValue >= curOperation.priorityValue){
                    polishNot = polishNot.plus(operationsStack.peek().textValue + ' ')
                    operationsStack.pop()
                }
                operationsStack.push((curOperation))
            }
            tempOperation = ""
        }
    }
    polishNot = polishNot.plus(tempDigit + ' ')
    while (operationsStack.isNotEmpty()) {
        polishNot = polishNot.plus(operationsStack.peek().textValue + ' ')
        operationsStack.pop()
    }
    return polishNot
}