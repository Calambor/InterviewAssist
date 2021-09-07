package com.example.common.app

import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver


public fun JdbcSqliteDriver.getVersion(): Int {
    val sqlCursor = this.executeQuery(null, "PRAGMA user_version;", 0, null)
    return sqlCursor.use { sqlCursor.getLong(0)!!.toInt() }
}

public fun JdbcSqliteDriver.setVersion(version: Int) {
    this.execute(null, String.format("PRAGMA user_version = %d;", version), 0, null)

}
