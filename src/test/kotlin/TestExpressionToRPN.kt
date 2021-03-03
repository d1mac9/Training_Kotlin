package src.main.kotlin

import expressionToRPN
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class TestExpressionToRPN : ShouldSpec() {
    init {
        context("Happy path small") {
            expressionToRPN("0-2") shouldBe "0 2 -"
        }
        context("Happy path long") {
            expressionToRPN("46+23.45-4*5+8") shouldBe "46 23.45 + 4 5 * - 8 +"
        }
    }
}