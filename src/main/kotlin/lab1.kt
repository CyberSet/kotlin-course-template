fun alignText(
    text: String,
    lineWidth: Int = 120,
): String {
    if(lineWidth < 1) throw IllegalArgumentException("Line width should be greater than 1")
    var result = ""
    for (curLine in text.lines()) {
        var noSpaces = curLine.trim()
        while (noSpaces.contains("  ")) noSpaces = noSpaces.replace("  ", " ")
        while (noSpaces.length > lineWidth) {
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
    return result.removeSuffix("\n")
}
