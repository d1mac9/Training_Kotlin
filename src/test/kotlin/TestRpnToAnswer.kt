package src.main.kotlin


import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import rpnToAnswer

class TestRpnToAnswer : ShouldSpec() {
    init {
        context("Expression with zero") {
            rpnToAnswer("0 2-") shouldBe -2
        }
        context("Happy path long expression") {
            rpnToAnswer("46 23.45 + 4 5 * - 8 +") shouldBe 57.45
        }
    }
}