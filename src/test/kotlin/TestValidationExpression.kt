package src.main.kotlin
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import validationExpression
import io.kotest.matchers.string.startWith

class TestValidationExpression : ShouldSpec() {
    init {
        context("Send incorrect symbols") {
            val exception = shouldThrow<Exception> {
                validationExpression("!")
            }
            exception.message!!.should(startWith("Введен некорректный символ: !"))
        }
        context("Send incorrect count of brackets") {
            val exception = shouldThrow<Exception> {
                validationExpression("(2+3-5))")
            }
            exception.message!!.should(startWith("Неверное расположение скобки(ок)"))
        }
        context("Send empty expression") {
            val exception = shouldThrow<Exception> {
                validationExpression("")
            }
            exception.message!!.should(startWith("Введенное выражение пустое"))
        }
        context("2 dots near") {
            val exception = shouldThrow<Exception> {
                validationExpression("..2+20")
            }
            exception.message!!.should(startWith("Две точки подряд"))
        }
        context("2 operators near") {
            val exception = shouldThrow<Exception> {
                validationExpression("-/2+20")
            }
            exception.message!!.should(startWith("Два оператора подряд"))
        }
        context("Happy path") {
            validationExpression("(2+3-5*9+(1-3))") shouldBe "(2+3-5*9+(1-3))"
        }
    }
}