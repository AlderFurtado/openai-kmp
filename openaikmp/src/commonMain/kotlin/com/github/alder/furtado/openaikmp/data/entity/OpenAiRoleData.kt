package com.github.alder.furtado.openaikmp.data.entity

import kotlinx.serialization.Serializable

@Serializable
internal enum class OpenAiRoleData(val value: String) {
    DEVELOPER("developer"),
    USER("user")
}