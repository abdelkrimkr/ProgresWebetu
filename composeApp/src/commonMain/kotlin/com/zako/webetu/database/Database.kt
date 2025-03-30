package com.zako.webetu.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers


@Database(
    entities = [] ,
    version =  1 ,
    exportSchema = true
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class Database : RoomDatabase()


fun  RoomDatabase.Builder<com.zako.webetu.database.Database>.getWebetuInstance(): com.zako.webetu.database.Database {
    return this
//        .addMigrations(MIGRATIONS)
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
