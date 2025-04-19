package dev.iad.fedevarchtemplate.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.browser.window
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.w3c.dom.Storage

// using a single module for all data related deps, can be separated to multiple modules if desired
fun createDataModule(
    baseServerUrl: String,
) = module {
    single<Storage> { window.localStorage }
    single<Json> {
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        }
    }
    single<HttpClient> {
        HttpClient(Js) {
            expectSuccess = true
            defaultRequest {
                url(urlString = baseServerUrl)
            }
            install(ContentNegotiation) {
                json(json = get())
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.BODY
                sanitizeHeader { it == HttpHeaders.Authorization }
            }
        }
    }
}

fun createRepositoriesModule() = module {
    singleOf(::DataRepository)
}
