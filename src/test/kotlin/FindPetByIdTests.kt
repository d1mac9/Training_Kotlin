package src.main.kotlin

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import methods.getRequest
import methods.postRequest
import requests.createPet
import java.net.HttpURLConnection
import io.kotest.matchers.string.beEmpty
import kotlin.random.Random

class FindPetByIdTests : ShouldSpec() {
        private fun url(id : Long) = """https://petstore.swagger.io/v2/pet/$id"""

        init {
            context("Successful creation pet") {

                getRequest(url = url(12)).apply {
                    statusCode shouldBe HttpURLConnection.HTTP_OK
                    String(data) shouldNotBe beEmpty()
                }
            }
            context("Try to find unexist pet"){
                getRequest(url = url(Random.nextLong(1000000000,9000000000))).apply {
                    statusCode shouldBe HttpURLConnection.HTTP_NOT_FOUND
                }
            }
        }
    }