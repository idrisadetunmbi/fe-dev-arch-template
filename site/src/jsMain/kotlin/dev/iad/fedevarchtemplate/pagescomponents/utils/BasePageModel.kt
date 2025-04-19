package dev.iad.fedevarchtemplate.pagescomponents.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

abstract class BasePageModel<T>(initialState: T) {
    private var mutableState: MutableState<T> = mutableStateOf(value = initialState)
    val state: State<T> = mutableState

    protected fun updateState(cb: T.() -> T) {
        mutableState.value = cb.invoke(mutableState.value)
    }
}
