package com.example.demo.dataflow

import com.example.Database
import com.example.common.dataflow.ClientServerFlow
import com.example.common.dataflow.CollectionOfItems
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.request
import kotlinx.coroutines.runBlocking

const val SERVER_URL: String = "http://localhost:8080/"

fun ClientServerFlow.clientGetResult(clientDb: Database): Sequence<CollectionOfItems> =
    sequence<CollectionOfItems> {

        this@sequence.yield(executeDbQuery(clientDb))
        var result: CollectionOfItems = runBlocking {
            HttpClient(CIO) {
                install(JsonFeature) {
                    serializer = GsonSerializer()
                }
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
            }.request(SERVER_URL + path) { }
        }

        this@sequence.yield(result)
    }

