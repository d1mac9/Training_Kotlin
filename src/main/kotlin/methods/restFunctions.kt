package methods

import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost


fun postRequest(url: String, body: String): Response {
    val (_, response) = url
        .httpPost()
        .jsonBody(body)
        .header(Headers.CONTENT_TYPE, value = "application/json; charset=utf-8")
        .responseString()
    return response
}
fun getRequest(url: String): Response {
    val (_, response) = url
        .httpGet()
        .header(Headers.CONTENT_TYPE, value = "application/json; charset=utf-8")
        .responseString()
    return response
}