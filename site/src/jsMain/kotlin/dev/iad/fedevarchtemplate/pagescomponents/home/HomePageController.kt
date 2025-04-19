package dev.iad.fedevarchtemplate.pagescomponents.home

import com.varabyte.kobweb.core.PageContext
import dev.iad.fedevarchtemplate.data.DataRepository
import dev.iad.fedevarchtemplate.pagescomponents.utils.BasePageModel
import dev.iad.fedevarchtemplate.utils.Async
import dev.iad.fedevarchtemplate.utils.mapAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class HomePageController(
    private val repository: DataRepository,
    private val coroutineScope: CoroutineScope,
    private val pageContext: PageContext,
) : BasePageModel<HomePageState>(initialState = HomePageState()) {
    fun fetchData() {
        updateState { copy(dataAsync = Async.Loading()) }
        coroutineScope.launch {
            val result = repository.getData().mapAsync()
            updateState { copy(dataAsync = result) }
        }
    }

    fun updateData() {
        updateState { copy(dataAsync = Async.Loading()) }
        coroutineScope.launch {
            val result = repository.updateData(title = "")
            if (result.isSuccess) {
                pageContext.router.tryRoutingTo(pathQueryAndFragment = "/new-page")
            }
        }
    }
}
