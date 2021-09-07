package com.example.common.dataflow

import com.example.Database

abstract class ClientServerFlow(
    val path: String
) {
    abstract fun executeDbQuery(db: Database) : CollectionOfItems

    fun clientGetResult(clientDb: Database): Sequence<CollectionOfItems> = sequence<CollectionOfItems> {
        yield(executeDbQuery(clientDb))
        // network call

        // yield full result

    }




}
