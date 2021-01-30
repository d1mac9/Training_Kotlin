import java.util.Scanner


fun main() {
    val scan = Scanner(System.`in`)
    println("Введите первое число : ")
    val number1: Double = scan.nextDouble()
    var method: String
    var number2: Double
    do {
        println("Введите метод (*,/,+,-) : ")
        method = scan.next()
        if ((method != "*") && (method != "/") && (method != "+") && (method != "-"))
            println("Вы ввели некорректное значение:<$method> ")

    } while ((method != "*") && (method != "/") && (method != "+") && (method != "-"))
    do {
        println("Введите второе число : ")
        number2 = scan.nextDouble()
        if ((method == "/") && (number2 == 0.00))
            println ("Делить на <$number2> нельзя")
    } while ((method == "/") && (number2 == 0.00))



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