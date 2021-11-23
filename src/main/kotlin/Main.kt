fun main() {
    var result: String = parseToReversePolishNot("3+4*2/(1-5)^2")
    println(result)
    println(calculateReversePolishNot(result))
    result = parseToReversePolishNot("-(1+2)*4+3")
    println(result)
    println(calculateReversePolishNot(result))
    result = parseToReversePolishNot("10-5")
    println(result)
    println(calculateReversePolishNot(result))
    result = parseToReversePolishNot("2+2.2+3+4-2+1-6*3.2")
    println(result)
    println(calculateReversePolishNot(result))
}