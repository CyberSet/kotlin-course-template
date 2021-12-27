import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import java.io.File

class shapeTool {
    private val json = Json {
        prettyPrint = true

        serializersModule = SerializersModule {
            polymorphic(Shape::class) {
                subclass(Circle::class)
                subclass(Square::class)
                subclass(Rectangle::class)
                subclass(Triangle::class)
            }
        }
    }

    fun encode(data: Shape): String {
        return json.encodeToString(data)
    }

    fun decode(data: String): Shape {
        return json.decodeFromString(data)
    }

    fun writeToFile(data: String, path: String) {
        File(path).printWriter().use { out ->
            out.println(data)
        }
    }

    fun readFromFile(path: String): String {
        return File(path).readText()
    }
}
