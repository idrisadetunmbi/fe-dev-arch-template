package dev.iad.fedevarchtemplate.pagescomponents.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun <T> rememberState(
    initialState: T,
): Pair<T, (cb: T.() -> T) -> Unit> {
    var state by remember { mutableStateOf(value = initialState) }
    val updateState: (cb: T.() -> T) -> Unit = {
        state = it.invoke(state)
    }
    return state to updateState
}
