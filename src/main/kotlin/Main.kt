fun main() {
    var text : String = """Умом — Россию не понять,
   Аршином   общим не измерить.
У   ней особенная стать —                                      
В Россию можно только верить. абракадрамымратакая
    """.trimIndent();
    text = alignText(text, 16, Alignment.LEFT);
    println(text);
}