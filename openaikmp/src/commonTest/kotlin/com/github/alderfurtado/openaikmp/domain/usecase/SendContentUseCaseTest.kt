package com.github.alderfurtado.openaikmp.domain.usecase

import com.github.alder.furtado.openaikmp.domain.entity.OpenAiInput
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiResponse
import com.github.alder.furtado.openaikmp.domain.entity.error.ErrorInternalServer
import com.github.alder.furtado.openaikmp.domain.entity.error.ErrorResourceNotFound
import com.github.alder.furtado.openaikmp.domain.entity.error.ErrorUnauthorizedToken
import com.github.alder.furtado.openaikmp.domain.repository.OpenAiRepository
import com.github.alder.furtado.openaikmp.domain.usecase.SendContentUseCase
import com.github.alderfurtado.openaikmp.util.stub.OpenAiInputStub
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SendContentUseCaseTest {

    class OpenAiRepositoryFake(private val callback: () -> OpenAiResponse): OpenAiRepository {
        override suspend fun sendContent(openAiInput: OpenAiInput, token: String): OpenAiResponse {
            return callback.invoke()
        }
    }

    @Test
    fun `given token and openAiInput valid when sendContent should return OpenAiResponse`()= runBlocking{
        val openAiRepositoryFake = OpenAiRepositoryFake(callback = {
            return@OpenAiRepositoryFake OpenAiResponse.OpenAiResponseSucceed(response = "")
        })
        val result = SendContentUseCase(openAiRepositoryFake).execute(OpenAiInputStub.stub(),"")

        assertEquals(result.content,"")
    }

    @Test
    fun `given token invalid when sendContent should return ErrorUnauthorizedToken`()= runBlocking{
        val openAiRepositoryFake = OpenAiRepositoryFake(callback = {
            throw ErrorUnauthorizedToken()
        })
        val error = assertFailsWith<ErrorUnauthorizedToken> {
            SendContentUseCase(openAiRepositoryFake).execute(OpenAiInputStub.stub(), "")
        }

        assertEquals(error.message,ErrorUnauthorizedToken().message)
    }

    @Test
    fun `given resource not found when sendContent should return ErrorResourceNotFound`()= runBlocking{
        val openAiRepositoryFake = OpenAiRepositoryFake(callback = {
            throw ErrorResourceNotFound()
        })
        val error = assertFailsWith<ErrorResourceNotFound> {
            SendContentUseCase(openAiRepositoryFake).execute(OpenAiInputStub.stub(), "")
        }

        assertEquals(error.message,ErrorResourceNotFound().message)
    }

    @Test
    fun `given error internal when sendContent should return ErrorInternalServer`()= runBlocking{
        val openAiRepositoryFake = OpenAiRepositoryFake(callback = {
            throw ErrorInternalServer()
        })
        val error = assertFailsWith<ErrorInternalServer> {
            SendContentUseCase(openAiRepositoryFake).execute(OpenAiInputStub.stub(), "")
        }

        assertEquals(error.message,ErrorInternalServer().message)
    }

    //TODO error timeout
//    @Test
//    fun `given token invalid when sendContent should return HttpRequestTimeoutException`()= runBlocking{
//        val openAiRepositoryFake = OpenAiRepositoryFake(callback = {
//            throw HttpRequestTimeoutException.()
//        })
//        val error = assertFailsWith<ErrorInternalServer> {
//            SendContentUseCase(openAiRepositoryFake).execute(OpenAiInputStub.stub(), "")
//        }
//
//        assertEquals(error.message,ErrorInternalServer().message)
//    }

    @Test
    fun `given error server internal when sendContent should return ErrorInternalServer`()= runBlocking{
        val openAiRepositoryFake = OpenAiRepositoryFake(callback = {
            throw ErrorInternalServer()
        })
        val error = assertFailsWith<ErrorInternalServer> {
            SendContentUseCase(openAiRepositoryFake).execute(OpenAiInputStub.stub(), "")
        }

        assertEquals(error.message,ErrorInternalServer().message)
    }
}