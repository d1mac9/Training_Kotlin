import java.util.Stack

fun calculate(expression: String): Double {
    validationExpression(expression)
    val prepared = preparingExpression(expression)
    val rpn = expressionToRPN(prepared)
    return rpnToAnswer(rpn)
}

private fun validationExpression(expression: String): String {
    var counter = 0

    if (expression.isEmpty()){
        throw Exception("Введенное выражение пустое")
    }
    if (expression[0] in "+*/" || expression[expression.length-1] in "-+*/"){
        throw Exception("Выражение начинается(заканчивается) с оператора(ом)")
    }

    for (token in expression.indices) {
        if (expression[token] != '*' && expression[token] != '/' && expression[token] != '+'
            && expression[token] != '-' && expression[token] != '.' && expression[token] != '('
            && expression[token] != ')' && expression[token] !in '0'..'9'
        ) {
            throw Exception("Введен некорректный символ: \"" + expression[token] + "\"")
        }

        if (expression[token] == '(') {
            counter++
        }
        if (expression[token] == ')') {
            counter--
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

private fun preparingExpression(expression: String): String {
    var preparedExpression = ""
    for (token in expression.indices) {
        val symbol = expression[token]
        if (symbol == '-') {
            if (token == 0) {
                preparedExpression += '0'
            } else if (expression[token - 1] == '(') {
                preparedExpression += '0'
            }
        }
        preparedExpression += symbol
    }
    return preparedExpression
}

private fun expressionToRPN(expression: String): String {
    var current = ""
    val stack = Stack<Char>()
    var priority: Int

    for (i in expression.indices) {
        priority = getPriority(expression[i])
        if (priority == 0) {
            current += expression[i]
        }
        if (priority == 1) {
            stack.push(expression[i])
        }
        if (priority > 1) {
            current += " "
            while (!stack.empty()) {
                if (getPriority(stack.peek()) >= priority) {
                    current += stack.pop()
                } else break
            }
            stack.push(expression[i])
        }
        if (priority == -1) {
            current += " "
            while (getPriority(stack.peek()) != 1) {
                current += stack.pop()
            }
            stack.pop()
        }
    }
    while (!stack.empty()) {
        current += stack.pop()
    }
    return current
}

private fun rpnToAnswer(rpn: String): Double {
    var operand = ""
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
                operand += rpn[j++]
                if (j == rpn.length) {
                    break
                }
                counter++
            }
            stack.push(operand.toDouble())
            operand = ""
        }
        if (getPriority(rpn[i]) > 1) {
            val a = stack.pop()
            val b = stack.pop()

            if (rpn[i] == '+') {
                stack.push(b + a)
            }
            if (rpn[i] == '-') {
                stack.push(b - a)
            }
            if (rpn[i] == '*') {
                stack.push(b * a)
            }
            if (rpn[i] == '/') {
                stack.push(b / a)
            }

        }
    }
    return stack.pop()
}

private fun getPriority(token: Char): Int {
    return if (token == '*' || token == '/') 3
    else if (token == '+' || token == '-') 2
    else if (token == '(') 1
    else if (token == ')') -1
    else 0
}