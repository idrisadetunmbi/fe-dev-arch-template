package dev.iad.fedevarchtemplate

import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.layout.HorizontalDividerStyle
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.modifyStyleBase
import org.jetbrains.compose.web.css.CSSMediaQuery
import org.jetbrains.compose.web.css.StylePropertyValue
import org.jetbrains.compose.web.css.px

@InitSilk
fun initSiteStyles(ctx: InitSilkContext) {
    ctx.stylesheet.registerStyle(cssSelector = "html") {
        cssRule(
            CSSMediaQuery.MediaFeature(
                name = "prefers-reduced-motion",
                value = StylePropertyValue("no-preference"),
            ),
        ) {
            Modifier.scrollBehavior(scrollBehavior = ScrollBehavior.Smooth)
        }
    }

    ctx.stylesheet.registerStyleBase(cssSelector = "body") {
        Modifier
            .fontFamily(
                "-apple-system", "BlinkMacSystemFont", "Segoe UI", "Roboto", "Oxygen", "Ubuntu",
                "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", "sans-serif"
            )
            .fontSize(value = 18.px)
            .lineHeight(value = 1.5)
    }

    ctx.theme.modifyStyleBase(style = HorizontalDividerStyle) {
        Modifier.fillMaxWidth()
    }

    ctx.theme.modifyStyleBase(style = ButtonStyle) {
        Modifier.fontWeight(value = FontWeight.Normal)
    }
}

@InitSilk
fun initTheme(ctx: InitSilkContext) {
    ctx.theme.palettes.light.background = Color.rgb(0xFFFFFF)
    ctx.theme.palettes.light.color = Colors.Black
    ctx.theme.palettes.dark.background = Color.rgb(0x06080B)
    ctx.theme.palettes.dark.color = Colors.White
}
