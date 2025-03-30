package com.zako.webetu.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

fun getAppDatabaseBuilder() : RoomDatabase.Builder<Database> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "webetu.db")
    return Room.databaseBuilder<Database>(
        name = dbFile.absolutePath,
    )
}