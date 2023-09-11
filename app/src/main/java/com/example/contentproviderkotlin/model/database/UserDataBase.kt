package com.example.contentproviderkotlin.model.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.contentproviderkotlin.model.common.ColumnSchema

class UserDataBaseLegacy(
    private val context: Context,
    private val databaseName: String,
    private val databaseVersion: Int) : SQLiteOpenHelper(context, databaseName, null, databaseVersion) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE ${ColumnSchema.TABLE_NAME} (" +
            "${ColumnSchema.COLUMN_FIRST_NAME} varchar(255), " +
            "${ColumnSchema.COLUMN_LAST_NAME} varchar(255), " +
            "${ColumnSchema.COLUMN_ADDRESS} varchar(255), " +
            "${ColumnSchema.COLUMN_PHONE_NUMBER} varchar(255))"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(
            "DROP TABLE ${ColumnSchema.TABLE_NAME}"
        )
    }
}