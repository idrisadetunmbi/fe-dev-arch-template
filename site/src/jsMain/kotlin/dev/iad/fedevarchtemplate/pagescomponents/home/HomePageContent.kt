package dev.iad.fedevarchtemplate.pagescomponents.home

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.silk.components.forms.Button
import dev.iad.fedevarchtemplate.models.ResponseData
import dev.iad.fedevarchtemplate.utils.Async
import org.jetbrains.compose.web.dom.Text

@Composable
fun HomePageContent(
    state: HomePageState,
    onClickRetryBtn: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (state.dataAsync) {
            is Async.Fail<*> -> Column {
                val dataAsync = state.dataAsync as Async.Fail<ResponseData>
                Text(value = "An error occurred: ${dataAsync.error.message}")
                Button(onClick = {
                    onClickRetryBtn.invoke()
                }) {
                    Text(value = "Retry")
                }
            }

            is Async.Loading<*> -> {
                // show loading indicator
            }

            is Async.Success<*> -> {
                val dataAsync = state.dataAsync as Async.Success<ResponseData>
                Text(value = dataAsync.invoke().title)
            }

            Async.Uninitialized -> {
                // no-op
            }
        }
    }
}
