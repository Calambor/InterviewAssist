package com.example.common.dataflow

import com.example.Database

public class GetMyQuestionsFlow : ClientServerFlow("questions") {
    override fun executeDbQuery(db: Database): CollectionOfItems {
        return CollectionOfItems(
            questions = db.questionQueries.selectAll().executeAsList()
        )
    }
}
