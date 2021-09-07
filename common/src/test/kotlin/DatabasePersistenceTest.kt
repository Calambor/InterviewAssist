import com.example.Database
import com.example.common.app.getVersion
import com.example.common.app.setVersion
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class DatabasePersistenceTest {

    lateinit var db: Database

    @BeforeEach
    fun setUp() {
        val driver: JdbcSqliteDriver = JdbcSqliteDriver("jdbc:sqlite:testPersistenceDatabase")
        if (driver.getVersion() == 1) {
            driver.execute(0, "DROP TABLE user", 0, null)
            driver.execute(0, "DROP TABLE commonDeprecated", 0, null)
            driver.setVersion(0)
        }
        if (driver.getVersion() == 0) {
            Database.Schema.create(driver)
            driver.setVersion(1)
        }

        db = Database(driver)
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun `Persistent database usable without creating it`() {
        db.userQueries.createUser("TestId", "Elon")

        val newDriver = JdbcSqliteDriver("jdbc:sqlite:testPersistenceDatabase")
        val newDb = Database(newDriver)

        val users = newDb.userQueries.selectAll().executeAsList()
        assertEquals(2, users.size)
        assertEquals("Bob", users[0].name)
        assertEquals("Elon", users[1].name)
    }
}
