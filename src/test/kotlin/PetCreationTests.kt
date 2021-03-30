package src.main.kotlin
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.beEmpty
import methods.getRequest
import methods.postRequest
import model.petCreated
import requests.createPet
import java.net.HttpURLConnection


class PetCreationTests : ShouldSpec() {
    private val url = "https://petstore.swagger.io/v2/pet"
    init {
        context("Successful creation pet") {
            postRequest(url = url, body = createPet()).apply {
                statusCode shouldBe HttpURLConnection.HTTP_OK
                String(data) shouldNotBe beEmpty()
            }
        }
        context("Try to send unsupported type of method"){
            getRequest(url = url).apply {
                statusCode shouldBe HttpURLConnection.HTTP_BAD_METHOD
            }
        }
    }
}