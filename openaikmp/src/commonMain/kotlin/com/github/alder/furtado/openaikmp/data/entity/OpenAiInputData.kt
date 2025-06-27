package com.github.alder.furtado.openaikmp.data.entity

import kotlinx.serialization.Serializable

@Serializable
internal data class OpenAiInputData(val role:String, val content: String)
