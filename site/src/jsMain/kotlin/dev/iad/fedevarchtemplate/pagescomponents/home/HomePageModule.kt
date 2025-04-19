package dev.iad.fedevarchtemplate.pagescomponents.home

import org.koin.dsl.module

val homePageModule = module {
    factory { params ->
        HomePageController(
            repository = get(),
            coroutineScope = params.get(),
            pageContext = params.get(),
        )
    }
}
