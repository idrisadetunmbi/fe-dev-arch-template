package dev.iad.fedevarchtemplate.utils

import androidx.compose.runtime.Stable

@Stable
sealed class Async<out T>(val complete: Boolean, val shouldLoad: Boolean, private val value: T?) {
    open operator fun invoke(): T? = value

    data object Uninitialized : Async<Nothing>(complete = false, shouldLoad = true, value = null), Incomplete

    data class Loading<out T>(
        private val value: T? = null,
    ) : Async<T>(complete = false, shouldLoad = false, value = value), Incomplete

    data class Success<out T>(private val value: T) : Async<T>(complete = true, shouldLoad = false, value = value) {
        override operator fun invoke(): T = value
    }

    data class Fail<out T>(
        val error: Throwable,
        private val value: T? = null,
    ) : Async<T>(complete = true, shouldLoad = true, value = value) {

        override fun equals(other: Any?): Boolean {
            if (other !is Fail<*>) return false
            val otherError = other.error
            return error::class == otherError::class &&
                    error.message == otherError.message &&
                    error.stackTraceToString() == otherError.stackTraceToString()
        }

        override fun hashCode(): Int =
            arrayOf(error::class, error.message, error.stackTraceToString()).contentHashCode()
    }
}

@Stable
interface Incomplete

val <T> Async<T>.isLoading
    get() = this is Async.Loading

val <T> Async<T>.isSuccess
    get() = this is Async.Success

val <T> Async<T>.isError
    get() = this is Async.Fail

val <T> Async<T>.isRefreshing
    get() = this is Async.Loading && this.invoke() != null
