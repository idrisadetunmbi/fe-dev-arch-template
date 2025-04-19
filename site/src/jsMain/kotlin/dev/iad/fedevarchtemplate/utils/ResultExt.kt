package dev.iad.fedevarchtemplate.utils

fun <T> Result<T>.mapAsync(): Async<T> = if (isSuccess) {
    Async.Success(value = getOrThrow())
} else {
    Async.Fail(error = exceptionOrNull()!!)
}

inline fun <T, R> Result<T>.flatMap(transform: (T) -> Result<R>): Result<R> = when {
    isSuccess -> transform.invoke(this.getOrThrow())
    else -> Result.failure(exception = exceptionOrNull() ?: Throwable())
}
