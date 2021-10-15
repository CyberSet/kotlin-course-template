enum class Alignment {
    LEFT,
    RIGHT,
    CENTER,
    JUSTIFY
}

fun alignText(
    text: String,
    lineWidth: Int = 120,
    alignment: Alignment = Alignment.LEFT
): String {
    var result = ""
    for(curLine in text.lines()) {
        var noSpaces = curLine.trim()
        while(noSpaces.contains("  ")) noSpaces = noSpaces.replace("  ", " ")
        while(noSpaces.length > lineWidth){
            result += noSpaces.substring(0 until lineWidth) + '\n'
            noSpaces = noSpaces.drop(lineWidth)
        }
        result += noSpaces + '\n'
    }
    return  result
}
