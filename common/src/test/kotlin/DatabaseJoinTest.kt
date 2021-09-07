import com.example.Database
import com.example.common.dataflow.mapQuestionsUsers
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class DatabaseJoinTest {

    lateinit var db: Database

    @BeforeEach
    fun setUp() {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        Database.Schema.create(driver)

        db = Database(driver)
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun `Join questions and users returns both`() {
        val questionsWithUsers = db.questionQueries.selectWithUsers(::mapQuestionsUsers).executeAsList()

        assertEquals(questionsWithUsers[0].second.name, "Mr. Sample")
        assertEquals(questionsWithUsers[0].first.question, "Life from database")
    }
}
