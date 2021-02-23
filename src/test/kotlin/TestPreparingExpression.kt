package src.main.kotlin

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import preparingExpression

class TestPreparingExpression : ShouldSpec() {
    init {
        context("Expression has 1 operand") {
            preparingExpression("-2") shouldBe "0-2"
        }
        context("Expression has brackets") {
            preparingExpression("(-2)") shouldBe "(0-2)"
        }
        context("Expression has dot before number in begin") {
            preparingExpression(".2+1") shouldBe "0.2+1"
        }
        context("Expression has dot before number in middle") {
            preparingExpression("6+.2+1") shouldBe "6+0.2+1"
        }
        context("Expression has dot after number in middle") {
            preparingExpression("6+2.+1") shouldBe "6+2+1"
        }
        context("Expression has dot after number in beginning") {
            preparingExpression("6.+2+1") shouldBe "6+2+1"
        }
        context("Expression has dot after number in the end") {
            preparingExpression("6+2+1.") shouldBe "6+2+1"
        }
        context("Expression has some dots") {
            preparingExpression(".6.+.2.+1.") shouldBe "0.6+0.2+1"
        }
        context("Happy pass") {
            preparingExpression("6+0.132+1") shouldBe "6+0.132+1"
        }
    }
}