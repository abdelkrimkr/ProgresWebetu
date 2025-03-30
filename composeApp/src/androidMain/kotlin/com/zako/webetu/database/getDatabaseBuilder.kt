package com.zako.webetu.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getAppDatabaseBuilder(context: Context): RoomDatabase.Builder<Database> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("webetu.db")
    return Room.databaseBuilder<Database>(
        context = appContext,
        name = dbFile.absolutePath
    )
}