package dev.iad.fedevarchtemplate.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import dev.iad.fedevarchtemplate.pagescomponents.home.HomePageContent
import dev.iad.fedevarchtemplate.pagescomponents.home.HomePageController
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Page
@Composable
fun HomePage() {
    val coroutineScope = rememberCoroutineScope()
    val pageContext = rememberPageContext()
    val homePageController = koinInject<HomePageController> {
        parametersOf(coroutineScope, pageContext)
    }
    val state by homePageController.state

    LaunchedEffect(Unit) {
        homePageController.fetchData()
    }

    HomePageContent(
        state = state,
        onClickRetryBtn = homePageController::fetchData,
    )
}
