package com.example.contentproviderkotlin.model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.contentproviderkotlin.di.CustomApplication
import com.example.contentproviderkotlin.model.common.ColumnSchema
import com.example.contentproviderkotlin.model.common.DATABASE_NAME
import com.example.contentproviderkotlin.model.database.UserDataBaseLegacy

object DatabaseHandler {

    private val context: Context by lazy {
        CustomApplication.customApplication ?: throw Exception("Custom Application not init??")
    }
    val userDataBaseRead: SQLiteDatabase by lazy {
        INSTANCE.readableDatabase
    }

    val userDataBaseWrite: SQLiteDatabase by lazy {
        INSTANCE.writableDatabase
    }

    private const val databaseVersion: Int = 1

    private val INSTANCE: UserDataBaseLegacy by lazy {
        createDatabase(context, DATABASE_NAME, databaseVersion)
    }

    private fun createDatabase(context: Context, databaseName: String, databaseVersion: Int): UserDataBaseLegacy {
        return UserDataBaseLegacy(context, databaseName, databaseVersion)
    }

    fun queryDB(): Cursor? =
        userDataBaseRead.query(
            ColumnSchema.TABLE_NAME,
            arrayOf(ColumnSchema.COLUMN_FIRST_NAME,
                ColumnSchema.COLUMN_LAST_NAME,
                ColumnSchema.COLUMN_ADDRESS,
                ColumnSchema.COLUMN_PHONE_NUMBER),
            null,
            null,
            null,
            null,
            null
        )
}
