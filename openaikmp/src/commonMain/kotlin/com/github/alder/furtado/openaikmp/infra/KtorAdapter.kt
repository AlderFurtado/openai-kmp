package com.github.alder.furtado.openaikmp.infra

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json

open class HttpResponseLocal(val content: Any?, val statusCode: HttpStatusCode, val headers: Headers?)

internal class KtorAdapter : Http {

    private lateinit var headersLocal: MutableMap<String, String>

    private fun client(): HttpClient {
        val httpClient = getHttpClient()
        httpClient.config {
            install(ContentNegotiation) {
                json()
            }
        }
        return httpClient
    }

    override fun addHeaders(headers: MutableMap<String, String>) {
        headersLocal = headers
    }

    override suspend fun get(url: String): String {
        val response = client().get(url){
            headers {
                headersLocal.forEach {
                    append(it.key, it.value)
                }

            }
        }
        return response.bodyAsText()
    }


    override suspend fun <I,HttpResponseLocal> post(url: String, body: I):HttpResponseLocal {
        val response = client().post(url){
            headers {
                headersLocal.forEach {
                    append(it.key, it.value)
                }
            }
            setBody(body = body as Any)
        }.also {
            println(it)
        }

        val responseLocal = HttpResponseLocal(
            content = response.bodyAsText(),
            statusCode = response.status,
            headers = response.headers
        )

        return responseLocal as HttpResponseLocal
    }
}