package dev.iad.fedevarchtemplate.models

data class ServerException(
    val code: Int,
    override val message: String,
) : Throwable(message = message)
