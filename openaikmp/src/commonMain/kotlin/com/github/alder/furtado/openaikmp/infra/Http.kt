package com.github.alder.furtado.openaikmp.infra

internal interface Http {
    fun addHeaders(headers:MutableMap<String, String>): Unit
    suspend fun get(url: String): String
    suspend fun <I,O> post(url: String, body: I): O
}