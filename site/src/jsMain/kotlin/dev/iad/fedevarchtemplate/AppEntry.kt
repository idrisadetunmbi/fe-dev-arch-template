package dev.iad.fedevarchtemplate

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import dev.iad.fedevarchtemplate.data.createDataModule
import dev.iad.fedevarchtemplate.data.createRepositoriesModule
import dev.iad.fedevarchtemplate.pagescomponents.home.homePageModule
import org.jetbrains.compose.web.css.vh
import org.koin.compose.KoinApplication

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    KoinApplication(
        application = {
            modules(
                createDataModule(baseServerUrl = "http://localhost:8080/"),
                createRepositoriesModule(),
                homePageModule,
            )
        },
        {
            SilkApp {
                Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) {
                    content()
                }
            }
        }
    )
}
