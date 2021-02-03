import java.io.BufferedReader
import java.io.File

fun main() {
    val bufferedReader: BufferedReader = File("src/main/resources/file.txt").bufferedReader()
    val originalText = bufferedReader.use { it.readText() }
    print("Перевернутая фраза: ")
    print(reverseText(originalText))
}