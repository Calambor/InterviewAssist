package com.example.common.dataflow

import com.example.Database

public abstract class ClientServerFlow(
    val path: String
) {
    abstract fun executeDbQuery(db: Database) : CollectionOfItems

}
