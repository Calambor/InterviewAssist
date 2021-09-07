import com.example.Database
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BasicDatabaseTest {

    lateinit var db: Database

    @BeforeEach
    fun setUp() {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)//
        Database.Schema.create(driver)

        db = Database(driver)
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun `Initial database contains sample user`() {
        val users = db.userQueries.selectAll().executeAsList()
        assertEquals(1, users.size)
        assertEquals("Mr. Sample", users[0].name)
    }

    @Test
    fun `Add user creates a new user`() {
        db.userQueries.createUser("TestId", "Elon")
        val users = db.userQueries.selectAll().executeAsList()
        assertEquals(2, users.size)
        assertEquals("Mr. Sample", users[0].name)
        assertEquals("Elon", users[1].name)
    }
}
