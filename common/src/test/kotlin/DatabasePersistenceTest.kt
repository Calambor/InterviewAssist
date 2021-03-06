import com.example.Database
import com.example.common.app.getVersion
import com.example.common.app.setVersion
import com.example.demo.Question
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

internal class DatabasePersistenceTest {

    lateinit var db: Database

    @BeforeEach
    fun setUp() {
        File("testPersistenceDatabase").delete()
        val driver: JdbcSqliteDriver = JdbcSqliteDriver("jdbc:sqlite:testPersistenceDatabase")
        if (driver.getVersion() == 0) {
            Database.Schema.create(driver)
            driver.setVersion(1)
        }

        db = Database(
            driver,
            questionAdapter = Question.Adapter(
                visibilityAdapter = EnumColumnAdapter()
            )
        )
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun `Persistent database usable without creating it`() {
        db.userQueries.createUser("TestId", "Elon")

        val newDriver = JdbcSqliteDriver("jdbc:sqlite:testPersistenceDatabase")
        val newDb = Database(
            newDriver,
            questionAdapter = Question.Adapter(
                visibilityAdapter = EnumColumnAdapter()
            )
        )

        val users = newDb.userQueries.selectAll().executeAsList()
        assertEquals(2, users.size)
        assertEquals("Mr. Sample", users[0].name)
        assertEquals("Elon", users[1].name)
    }
}
