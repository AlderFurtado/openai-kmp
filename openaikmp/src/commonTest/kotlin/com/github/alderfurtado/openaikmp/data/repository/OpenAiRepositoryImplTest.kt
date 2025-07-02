package com.github.alderfurtado.openaikmp.data.repository

import com.github.alder.furtado.openaikmp.data.repository.OpenAiRepositoryImpl
import com.github.alder.furtado.openaikmp.domain.entity.error.ErrorInternalServer
import com.github.alder.furtado.openaikmp.domain.entity.error.ErrorResourceNotFound
import com.github.alder.furtado.openaikmp.domain.entity.error.ErrorUnauthorizedToken
import com.github.alderfurtado.openaikmp.util.stub.OpenAiInputStub
import com.github.alderfurtado.openaikmp.util.fake.HttpInternalServerErrorPostFake
import com.github.alderfurtado.openaikmp.util.fake.HttpOkPostFake
import com.github.alderfurtado.openaikmp.util.fake.HttpResourceNotFoundPostFake
import com.github.alderfurtado.openaikmp.util.fake.HttpTokenInvalidPostFake
import com.github.alderfurtado.openaikmp.util.stub.OpenAiResponseDataStub
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class OpenAiRepositoryImplTest {

    @Test
    fun `given a token invalid when sendContent then should throw ErrorUnauthorizedToken`() = runBlocking{
        val httpTokenInvalidPostFake = HttpTokenInvalidPostFake()

        val error = assertFailsWith<ErrorUnauthorizedToken> {
            OpenAiRepositoryImpl(httpTokenInvalidPostFake).sendContent(OpenAiInputStub.stub(),"")
        }

        assertEquals(error.message,ErrorUnauthorizedToken().message)
    }

    @Test
    fun `given a resource not found when sendContent then should throw ErrorResourceNotFound`() = runBlocking{
        val httpResourceNotFoundPostFake = HttpResourceNotFoundPostFake()
        val error = assertFailsWith<ErrorResourceNotFound> {
            OpenAiRepositoryImpl(httpResourceNotFoundPostFake).sendContent(OpenAiInputStub.stub(),"")
        }

        assertEquals(error.message,ErrorResourceNotFound().message)
    }

    @Test
    fun `given a internal server error when sendContent then should throw ErrorInternalServer`() = runBlocking{
        val httpInternalServerErrorPostFake = HttpInternalServerErrorPostFake()
        val error = assertFailsWith<ErrorInternalServer> {
            OpenAiRepositoryImpl(httpInternalServerErrorPostFake).sendContent(OpenAiInputStub.stub(),"")
        }

        assertEquals(error.message, ErrorInternalServer().message)
    }

    @Test
    fun `given status code 2xx when sendContent then return OpenAiResponse`() = runBlocking{
        val httpOkPostFake = HttpOkPostFake()

        val result = OpenAiRepositoryImpl(httpOkPostFake).sendContent(OpenAiInputStub.stub(),"")

        assertEquals(result.content, OpenAiResponseDataStub.stub().output[0].content[0].text)
    }
}