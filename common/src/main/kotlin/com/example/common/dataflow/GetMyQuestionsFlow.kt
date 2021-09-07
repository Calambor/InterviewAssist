package com.example.common.dataflow

import com.example.Database

class GetMyQuestionsFlow : ClientServerFlow("questions") {
    override fun executeDbQuery(db: Database): CollectionOfItems {
        return CollectionOfItems(
            questions = db.questionQueries.selectAll().executeAsList()
        )
    }
}
