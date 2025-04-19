package dev.iad.fedevarchtemplate.pagescomponents.home

import dev.iad.fedevarchtemplate.models.ResponseData
import dev.iad.fedevarchtemplate.utils.Async

data class HomePageState(
    val dataAsync: Async<ResponseData> = Async.Uninitialized,
)
