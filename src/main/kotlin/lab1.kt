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
    when{
        (alignment == Alignment.LEFT) -> {
            for(curLine in text.lines()) {
                var noSpaces = curLine.trim()
                while(noSpaces.contains("  ")) noSpaces = noSpaces.replace("  ", " ")
                while(noSpaces.length > lineWidth){
                    var spaceShift = 1
                    var lastSpace = noSpaces.substring(0 until lineWidth + 1).lastIndexOf(' ')
                    if (lastSpace == -1) {
                        lastSpace = lineWidth
                        spaceShift = 0
                    }
                    result += noSpaces.substring(0 until lastSpace) + '\n'
                    noSpaces = noSpaces.drop(lastSpace + spaceShift)
                }
                result += noSpaces + '\n'
            }
        }
    }
    return result.removeSuffix("\n")
}
