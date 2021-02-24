import java.util.Stack

fun calculate(expression: String): Double {
    validationExpression(expression)
    val prepared = preparingExpression(expression)
    val rpn = expressionToRPN(prepared)
    return rpnToAnswer(rpn)
}

fun validationExpression(expression: String): String {
    var counter = 0

    if (expression.isEmpty()) {
        throw Exception("Введенное выражение пустое")
    }
    if (expression[0] in "+*/" || expression[expression.length - 1] in "-+*/") {
        throw Exception("Выражение начинается(заканчивается) с оператора(ом)")
    }

    for (token in expression.indices) {
        if (expression[token] != '*' && expression[token] != '/' && expression[token] != '+'
            && expression[token] != '-' && expression[token] != '.' && expression[token] != '('
            && expression[token] != ')' && expression[token] !in '0'..'9'
        ) {
            throw Exception("Введен некорректный символ: \"" + expression[token] + "\"")
        }
        if (expression[token] in "*/+-" && expression[token + 1] in "*/+-") {
            throw Exception("Два оператора подряд")
        }
        if (expression[token] in "." && expression[token + 1] in ".") {
            throw Exception("Две точки подряд")
        }
        when (expression[token]) {
            '(' -> counter++
            ')' -> counter--
        }
        if (counter < 0) {
            throw Exception("Неверное расположение скобки(ок)")
        }
    }

    if (counter != 0) {
        throw Exception("Неверное расположение скобки(ок)")
    }

    return expression
}

fun preparingExpression(expression: String): String {
    val preparedExpression: StringBuilder = StringBuilder()
    for (token in expression.indices) {
        val symbol = expression[token]
        when (symbol) {
            '-' -> {
                if (token == 0) {
                    preparedExpression.append('0')
                } else if (expression[token - 1] == '(') {
                    preparedExpression.append('0')
                }
            }
            '.' -> {
                if (token == 0) {
                    preparedExpression.append('0')
                } else if (expression[token - 1] !in '0'..'9') {
                    preparedExpression.append('0')
                } else if (token == expression.length - 1) {
                    continue
                } else if (expression[token + 1] !in '0'..'9') {
                    continue
                }
            }
        }
        preparedExpression.append(symbol)
    }
    return preparedExpression.toString()
}

fun expressionToRPN(expression: String): String {
    val current: StringBuilder = StringBuilder()
    val stack = Stack<Char>()
    var priority: Int

    for (i in expression.indices) {
        priority = getPriority(expression[i])

        when (priority) {
            0 -> current.append(expression[i])
            1 -> stack.push(expression[i])
            2, 3 -> {
                current.append(" ")
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) {
                        current.append(stack.pop())
                        current.append(" ")
                    } else break
                }
                stack.push(expression[i])
            }
            -1 -> {
                current.append(" ")
                while (getPriority(stack.peek()) != 1) {
                    current.append(stack.pop())
                }
                stack.pop()
            }
        }
    }
    while (!stack.empty()) {
        current.append(" ")
        current.append(stack.pop())
    }
    return current.toString()
}

fun rpnToAnswer(rpn: String): Double {
    var operand: StringBuilder = StringBuilder()
    val stack = Stack<Double>()
    var counter = 0
    var j: Int

    for (i in rpn.indices) {
        if (counter > 1) {
            counter--
            continue
        }
        if (rpn[i] == ' ') {
            continue
        }
        if (getPriority(rpn[i]) == 0) {
            j = i
            counter = 0
            while (rpn[j] != ' ' && getPriority(rpn[j]) == 0) {
                operand.append(rpn[j++])
                if (j == rpn.length) {
                    break
                }
                counter++
            }
            stack.push(operand.toString().toDouble())
            operand = StringBuilder()
        }
        if (getPriority(rpn[i]) > 1) {
            val a = stack.pop()
            val b = stack.pop()

            when (rpn[i]) {
                '+' -> stack.push(b + a)
                '-' -> stack.push(b - a)
                '*' -> stack.push(b * a)
                '/' -> stack.push(b / a)
            }

        }
    }
    return stack.pop()
}

private fun getPriority(token: Char): Int {
    return when (token) {
        '*', '/' -> 3
        '+', '-' -> 2
        '(' -> 1
        ')' -> -1
        else -> 0
    }
}