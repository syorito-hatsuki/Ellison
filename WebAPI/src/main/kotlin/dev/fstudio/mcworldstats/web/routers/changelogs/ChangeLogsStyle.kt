package dev.fstudio.mcworldstats.web.routers.changelogs

import dev.fstudio.mcworldstats.respondCss
import io.ktor.application.*
import io.ktor.routing.*
import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.border
import kotlinx.css.properties.scale
import kotlinx.css.properties.transform

fun Route.routeChangeLogsCss() {
    TODO("Fix bad css")
    get("changelogs/styles.css") {
        call.respondCss {
            root {
                fontSize = 10.px
            }
            before {
                boxSizing = BoxSizing.borderBox
            }
            after {
                boxSizing = BoxSizing.borderBox
            }
            body {
                minHeight = 100.vh

            }
            rule(".container") {
                maxHeight = 100.rem
                margin(LinearDimension("0"), LinearDimension.auto)
                padding(LinearDimension("0"), LinearDimension("2rem"), LinearDimension("2rem"))
            }
            rule(".heading") {
                fontSize = 4.rem
                fontWeight = FontWeight("500")
                lineHeight = LineHeight("1.5")
                textAlign = TextAlign.center
                padding(LinearDimension("3.5rem"), LinearDimension("0"))
                color = Color("#1a1a1a")
            }
            rule(".heading span") {
                display = Display.block
            }
            rule(".gallery") {
                display = Display.flex
                flexWrap = FlexWrap.wrap
                fontSize = 16.pt
                textAlign = TextAlign.center
                height = 100.px
//                margin(LinearDimension("-1rem"), LinearDimension("-1rem"))
            }
            rule(".gallery-item") {
                flex(1.0, 0.0, LinearDimension("24rem"))
                margin(LinearDimension("1rem"))
//                boxShadow(
//                    Color("#000000").withAlpha(0.4),
//                    LinearDimension("0.3rem"),
//                    LinearDimension("0.4rem"),
//                    LinearDimension("0.4rem")
//                )
                border(LinearDimension("1px"), BorderStyle.solid, Color.black)
                overflow = Overflow.hidden
            }
            rule(".gallery-image") {
                display = Display.block
                width = LinearDimension("100%")
                height = LinearDimension("100%")
                objectFit = ObjectFit.cover
                textAlign = TextAlign.center
//                transition("transform", 400.ms, Timing.easeOut)
            }
            rule(".gallery-image:hover") {
                transform {
                    scale(1.15)
                }
            }
            supports("(display: grid)") {
                rule("gallery") {
                    display = Display.grid
                    gridTemplateColumns = GridTemplateColumns.repeat("auto-fit, minmax(24rem, 1fr)")
                    gap = LinearDimension("2rem")
                }
                rule(".gallery, .gallery-item") {
//                    margin(LinearDimension("0"))
                }
            }
        }
    }
}