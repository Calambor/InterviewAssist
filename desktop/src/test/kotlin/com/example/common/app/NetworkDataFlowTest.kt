package com.example.common.app

import com.example.Database
import com.example.common.dataflow.GetMyQuestionsFlow
import com.example.demo.Question
import com.example.demo.dataflow.clientGetResult
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class NetworkDataFlowTest {

    lateinit var db: Database

    @BeforeEach
    fun setUp() {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        Database.Schema.create(driver)

        db = Database(
            driver,
            questionAdapter = Question.Adapter(
                visibilityAdapter = EnumColumnAdapter()
            )
        )
    }

    // Runs real network traffic to localhost
    @Test
    fun `Network returns questions`() {
        db.questionQueries.delete("sample_life_qid")
        val flow = GetMyQuestionsFlow()
        val result = flow.clientGetResult(db)
        val localItems = result.first()
        val networkItems = result.elementAt(1)

        assertEquals(0, localItems.questions!!.size)
        assertEquals(2, networkItems.questions!!.size)
        assertEquals("Life from database", networkItems.questions!![0].question)
        assertEquals("Question from Server", networkItems.questions!![1].question)
    }

}
