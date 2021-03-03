package src.main.kotlin
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import validationExpression
import io.kotest.core.test.TestResult

class TestValidationExpression : ShouldSpec() {
    init {
//        context("Send incorrect symbols") {
//            validationExpression("!") shouldBe Exception("Введен некорректный символ: \"!\"") //как парсить эксепшн?
//        }
//        context("Send incorrect count of brackets") {
//            validationExpression("(2+3-5))") shouldBe Exception("Неверное расположение скобки(ок)")
//        }
//        context("Send empty expression") {
//            validationExpression("") shouldBe Exception("Введенное выражение пустое")
//        }
//        context("2 dots near") {
//            validationExpression("..2+20") shouldBe Exception("Две точки подряд")
//        }
//        context("2 operators near") {
//            validationExpression("-/2+20") shouldBe Exception("Два оператора подряд")
//        }
        context("Happy path") {
            validationExpression("(2+3-5*9+(1-3))") shouldBe "(2+3-5*9+(1-3))"
        }
    }
}