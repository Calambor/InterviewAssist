package com.example

import com.example.common.app.Visibility
import com.example.common.dataflow.CollectionOfItems
import com.example.common.dataflow.GetMyQuestionsFlow
import com.example.demo.Question
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.html.respondHtml
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.p
import kotlinx.html.title

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        gson()
    }
    routing {
        get(GetMyQuestionsFlow().path) {
            call.respond(
                CollectionOfItems(
                    questions = listOf(
                        Question("dummy", 1, "Dummy question", "sample_user_1", Visibility.PUBLIC)
                    )
                )
            )
        }
        get("/") {
            call.respondHtml {
                head {
                    title { +"Ktor: Docker" }
                }
                body {
                    val runtime = Runtime.getRuntime()
                    p { +"Hello from Ktor Netty, more than a sample application." }
                    p { +"Runtime.getRuntime().availableProcessors(): ${runtime.availableProcessors()}" }
                    p { +"Runtime.getRuntime().freeMemory(): ${runtime.freeMemory()}" }
                    p { +"Runtime.getRuntime().totalMemory(): ${runtime.totalMemory()}" }
                    p { +"Runtime.getRuntime().maxMemory(): ${runtime.maxMemory()}" }
                    p { +"System.getProperty(\"user.name\"): ${System.getProperty("user.name")}" }
                }
            }
        }
    }
}
