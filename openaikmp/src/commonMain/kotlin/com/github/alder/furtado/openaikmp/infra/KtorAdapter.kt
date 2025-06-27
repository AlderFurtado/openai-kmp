package com.github.alder.furtado.openaikmp.infra

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
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


    override suspend fun <I> post(url: String, body: I):String {
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

        return response.bodyAsText()
    }
}