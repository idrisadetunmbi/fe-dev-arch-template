package dev.iad.fedevarchtemplate.data.utils

import dev.iad.fedevarchtemplate.models.ServerException
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import kotlinx.serialization.Serializable

suspend fun <R> runApiRequest(request: suspend () -> R): Result<R> {
    val result = runCatching { request() }
    return if (result.isSuccess) result
    else Result.failure(exception = mapServerException(exception = result.exceptionOrNull()!!))
}

// this is based on a how a specific backend server responds with an error response to a request
private suspend fun mapServerException(exception: Throwable) = when (exception) {
    is ResponseException -> {
        val code = exception.response.status.value
        val errorMessage = exception.response.body<Data>().message
        ServerException(code = code, message = errorMessage)
    }

    else -> exception
}

@Serializable
private data class Data(val message: String)
