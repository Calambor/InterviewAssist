package com.example.common.dataflow

import com.example.Database
import com.example.demo.Question
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class GetMyQuestionsFlowTest {

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

    @Test
    fun `Flow returns questions`() {
        val questionsFlow = GetMyQuestionsFlow()
        val initialResult = questionsFlow.executeDbQuery(db)
        assertEquals(initialResult.questions!!.first().question, "Life from database")
    }
}
