package com.github.alderfurtado.openaikmp.util.fake

import com.github.alder.furtado.openaikmp.infra.Http
import com.github.alder.furtado.openaikmp.infra.HttpResponseLocal
import io.ktor.http.HttpStatusCode

class HttpInternalServerErrorPostFake: Http {
    private var headersLocal: MutableMap<String, String> = mutableMapOf()

    override fun addHeaders(headers: MutableMap<String, String>) {
        headersLocal = headers
    }

    override suspend fun get(url: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun <I, HttpResponseLocal> post(url: String, body: I): HttpResponseLocal {
        return HttpResponseLocal(
            content = null,
            statusCode = HttpStatusCode.InternalServerError,
            headers = null
        ) as HttpResponseLocal
    }

}
