package com.github.alder.furtado.openaikmp.domain.usecase

import com.github.alder.furtado.openaikmp.domain.entity.ErrorContentLessThanTwoCharacters
import com.github.alder.furtado.openaikmp.domain.entity.ErrorInstructionLessThanTwoCharacters
import com.github.alder.furtado.openaikmp.domain.entity.ErrorUnknown
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiInput
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiResponse
import com.github.alder.furtado.openaikmp.domain.repository.OpenAiRepository
import io.ktor.client.plugins.HttpRequestTimeoutException

internal class SendContentUseCase(private val openAiRepository: OpenAiRepository) {
    suspend fun execute(openAiInput: OpenAiInput, token: String): OpenAiResponse {
        val error = ErrorUnknown()
        try {
            val result = openAiRepository.sendContent(openAiInput,token)
            result.content?.let {
                return OpenAiResponse.OpenAiResponseSucceed(result.content)
            }
            return OpenAiResponse.OpenAiResponseError(error = error.message as String)
        }catch (e: ErrorInstructionLessThanTwoCharacters){
            return OpenAiResponse.OpenAiResponseError(error = e.message as String)
        }catch (e: ErrorContentLessThanTwoCharacters){
            return OpenAiResponse.OpenAiResponseError(error = e.message as String)
        }catch (e: HttpRequestTimeoutException){
            return OpenAiResponse.OpenAiResponseError(error = "Error from Timeout")
        }catch (e: Exception){
            return OpenAiResponse.OpenAiResponseError(error = error.message as String)
        }
    }
}