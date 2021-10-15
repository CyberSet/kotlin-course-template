fun main() {
    var text : String = """Умом — Россию не понять,
   Аршином   общим не измерить.
У   ней особенная стать —                                      
В Россию можно только верить.  
    """.trimIndent();
    text = alignText(text, 20, Alignment.LEFT);
    println(text);
}