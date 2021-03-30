package src.main.kotlin

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.beEmpty
import methods.getRequest
import methods.postRequest
import requests.createPet
import java.net.HttpURLConnection

class FindPetByStatusTests: ShouldSpec() {
    private fun url(type : String) = """https://petstore.swagger.io/v2/pet/findByStatus?status=$type"""

    init {
        context("Find available pets") {
            getRequest(url = url("available")).apply {
                statusCode shouldBe HttpURLConnection.HTTP_OK
                String(data) shouldNotBe beEmpty()
            }
        }
        context("Find pending pets") {
            getRequest(url = url("pending")).apply {
                statusCode shouldBe HttpURLConnection.HTTP_OK
                String(data) shouldNotBe beEmpty()
            }
        }
        context("Find sold pets") {
            getRequest(url = url("sold")).apply {
                statusCode shouldBe HttpURLConnection.HTTP_OK
                String(data) shouldNotBe beEmpty()
            }
            }
        context("Invalid method") {
            postRequest(url = url("sold"), body = createPet()).apply {
                statusCode shouldBe HttpURLConnection.HTTP_BAD_METHOD
            }
        }
    }
}