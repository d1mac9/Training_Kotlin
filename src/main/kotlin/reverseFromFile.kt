import java.io.BufferedReader
import java.io.File
import java.util.Scanner

fun main() {
    val scan = Scanner(System.`in`)
    println("Введите путь до файла: ")
    val filePath: String = scan.nextLine()
    val bufferedReader: BufferedReader = File(filePath).bufferedReader()
    val originalText = bufferedReader.use { it.readText() }
    print("Перевернутая фраза: ")
    print(reverseText(originalText))
}