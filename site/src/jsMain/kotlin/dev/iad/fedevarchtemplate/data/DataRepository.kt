package dev.iad.fedevarchtemplate.data

import dev.iad.fedevarchtemplate.data.utils.runApiRequest
import dev.iad.fedevarchtemplate.models.RequestData
import dev.iad.fedevarchtemplate.models.ResponseData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.w3c.dom.Storage

class DataRepository(
    private val storage: Storage,
    private val httpClient: HttpClient
) {
    suspend fun getData(): Result<ResponseData> = runApiRequest {
        httpClient
            .get(urlString = "path-to-api-resource")
            .body<ResponseData>()
    }

    suspend fun updateData(title: String): Result<Unit> = runApiRequest {
        httpClient
            .post(urlString = "path-to-api-resource") {
                contentType(type = ContentType.Application.Json)
                setBody(body = RequestData(title = title))
            }
            .body<Unit>()
    }
}
