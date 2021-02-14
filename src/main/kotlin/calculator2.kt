import java.lang.Exception
import java.util.Stack

fun main() {
    val stackOperands = Stack<Double>()
    val stackPartDigits = Stack<Char>()
    val stackOperators = Stack<Char>()
    var ch: Char = ' '
    var d: Double = 0.00
    var s: Char = ' '
    var digit: String = ""
    var operand: String = ""

    val a = readLine()!!
    for (i in a.indices) {
        ch = a[i]

        if ((ch in '0'..'9') || (ch == '.')) {
            stackPartDigits.push(ch)
            if (i == a.length - 1) {
                while (!stackPartDigits.empty()) {
                    digit = stackPartDigits.pop().toString()
                    operand += digit
                }
                stackOperands.push(operand.reversed().toDouble())
                operand = ""
            }
        } else if (ch.equals('*') || ch.equals('/') || ch.equals('+') ||
            ch.equals('-') || ch.equals('(') || ch.equals(')')) {
            if (!stackPartDigits.empty() || i == a.length - 1) {
                while (!stackPartDigits.empty()) {
                    digit = stackPartDigits.pop().toString()
                    operand += digit
                }
                stackOperands.push(operand.reversed().toDouble())
                operand = ""
            }
            stackOperators.push(ch)
        } else {
            throw Exception("Введен недопустимый символ! $ch")
        }
    }

    fun showStackDigits() {
        while (!stackOperands.empty()) {
            d = stackOperands.pop()
            println(d)
        }
    }

    fun showStackOperators() {
        while (!stackOperators.empty()) {
            s = stackOperators.pop()
            println(s)
        }
    }
    showStackDigits()
    showStackOperators()
}