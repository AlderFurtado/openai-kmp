package com.github.alder.furtado.openaikmp.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class OpenAiResponseData(
    @SerialName("id") val id: String,
    @SerialName("object") val objectType: String,
    @SerialName("created_at") val createdAt: Long,
    @SerialName("status") val status: String,
    @SerialName("background") val background: Boolean,
    @SerialName("error") val error: String? = null,
    @SerialName("incomplete_details") val incompleteDetails: String? = null,
    @SerialName("instructions") val instructions: String? = null,
    @SerialName("max_output_tokens") val maxOutputTokens: Int? = null,
    @SerialName("max_tool_calls") val maxToolCalls: Int? = null,
    @SerialName("model") val model: String,
    @SerialName("output") val output: List<Output>,
    @SerialName("parallel_tool_calls") val parallelToolCalls: Boolean,
    @SerialName("previous_response_id") val previousResponseId: String? = null,
    @SerialName("reasoning") val reasoning: Reasoning,
    @SerialName("service_tier") val serviceTier: String,
    @SerialName("store") val store: Boolean,
    @SerialName("temperature") val temperature: Double,
    @SerialName("text") val text: TextWrapper,
    @SerialName("tool_choice") val toolChoice: String,
    @SerialName("tools") val tools: List<String>,
    @SerialName("top_logprobs") val topLogprobs: Int,
    @SerialName("top_p") val topP: Double,
    @SerialName("truncation") val truncation: String,
    @SerialName("usage") val usage: Usage,
    @SerialName("user") val user: String? = null,
    @SerialName("metadata") val metadata: Map<String, String>
)

@Serializable
data class Output(
    @SerialName("id") val id: String,
    @SerialName("type") val type: String,
    @SerialName("status") val status: String,
    @SerialName("content") val content: List<Content>,
    @SerialName("role") val role: String
)

@Serializable
data class Content(
    @SerialName("type") val type: String,
    @SerialName("annotations") val annotations: List<String>,
    @SerialName("logprobs") val logprobs: List<String>,
    @SerialName("text") val text: String
)

@Serializable
data class Reasoning(
    @SerialName("effort") val effort: String? = null,
    @SerialName("summary") val summary: String? = null
)

@Serializable
data class TextWrapper(
    @SerialName("format") val format: Format
)

@Serializable
data class Format(
    @SerialName("type") val type: String
)

@Serializable
data class Usage(
    @SerialName("input_tokens") val inputTokens: Int,
    @SerialName("input_tokens_details") val inputTokensDetails: InputTokensDetails,
    @SerialName("output_tokens") val outputTokens: Int,
    @SerialName("output_tokens_details") val outputTokensDetails: OutputTokensDetails,
    @SerialName("total_tokens") val totalTokens: Int
)

@Serializable
data class InputTokensDetails(
    @SerialName("cached_tokens") val cachedTokens: Int
)

@Serializable
data class OutputTokensDetails(
    @SerialName("reasoning_tokens") val reasoningTokens: Int
)

