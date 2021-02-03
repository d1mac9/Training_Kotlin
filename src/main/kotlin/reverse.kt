import java.util.Scanner

fun main() {
    val scan = Scanner(System.`in`)
    println("Введите фразу, которую нужно перевернуть: ")
    val originalText: String = scan.nextLine()
    print("Перевернутая фраза: ")
    print(reverseText(originalText))
}