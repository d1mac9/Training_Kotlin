import java.util.Scanner


fun main() {
    val scan = Scanner(System.`in`)
    println("Введите первое число : ")
    val number1: Double = scan.nextDouble()
    var method: String
    do {
        println("Введите метод (*,/,+,-) : ")
        method = scan.next()
    } while ((method != "*") && (method != "/") && (method != "+") && (method != "-"))
    println("Введите второе число : ")
    val number2: Double = scan.nextDouble()


    fun calculator(number1: Double, number2: Double, method: String): Double {
        return when (method) {
            "*" -> number1 * number2
            "/" -> number1 / number2
            "+" -> number1 + number2
            "-" -> number1 - number2
            else -> 0.00
        }
    }
    println("Результат: " + calculator(number1, number2, method))
}