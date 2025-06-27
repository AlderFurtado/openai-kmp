package com.github.alder.furtado.openaikmp.data.entity

import kotlinx.serialization.Serializable

@Serializable
internal data class OpenAiRequestData(val model: String, val input: List<OpenAiInputData>)
