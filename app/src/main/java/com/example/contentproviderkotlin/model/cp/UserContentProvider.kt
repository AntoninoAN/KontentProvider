package com.example.contentproviderkotlin.model.cp

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.example.contentproviderkotlin.model.DatabaseHandler
import com.example.contentproviderkotlin.model.common.CP_AUTHORITY
import com.example.contentproviderkotlin.model.common.CP_CONTENT_PATH
import com.example.contentproviderkotlin.model.common.CP_MULTIPLE_RECORD_MIME_TYPE
import com.example.contentproviderkotlin.model.common.CP_SINGLE_RECORD_MIME_TYPE

class UserContentProvider: ContentProvider() {

    private lateinit var dbRead: SQLiteDatabase
    private lateinit var dbWrite: SQLiteDatabase

    /**
     * Utility function to define the matches for an incoming URI request
     * This will be also defined to return 0 or 1 to describe the MIME type.
     * In this case, 1 will be returned for MIME types that request a "row element"
     * 0 will be returned for MIME types that request a "table element".
     */
    private val uriMatcher: UriMatcher by lazy {
        UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(CP_AUTHORITY, "$CP_CONTENT_PATH/#",1)
            addURI(CP_AUTHORITY, "$CP_CONTENT_PATH",0)
        }
    }

    /**
     * The first callback in the CP. Similar to onCreate in Activity @see #Activity
     * We will use to "preload" or initialize any reference in this class.
     * @return depending if you are successfully initializing this CP.
     */
    override fun onCreate(): Boolean {
        // ideally we will init the database object. Because this is a SQLite implementation
        // and we need to define a writable or readable action.
        dbRead = DatabaseHandler.userDataBaseRead
        dbWrite = DatabaseHandler.userDataBaseWrite

        return true
    }

    /**
     * This represents a "READ" transaction to against your DB. Could be called from multiple threads.
     * In a traditional query args your application needs to resolve all this arguments.
     * @param projection this represents the list of columns that will be returned in your read transaction.
     * Null means all of the columns.
     * @param selection this represents your 'WHERE' clause. Null means no where clause.
     * If not null then selectionArgs needs to be notNull.
     * If not null then this args needs to exists in your column names.
     * @param selectionArgs this represents the values to WHERE at. Null means no where clause.
     * @param sortOrder this represents your 'ORDER BY'. Null means natural order.
     * @param uri this represents the Android content path to find and query your CP.
     * URI can be of 2 types, query all the table or an specific row item.
     * the general path of URI can be
     * content://full.qualified.package.name/table_name/
     * content://full.qualified.package.name/table_name/row_position
     * @return The result cursor from your DB. This could be empty has no rows or table exists.
     */
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        // For simplicity I will only support SELECT * FROM TABLENAME
        return try {
            DatabaseHandler.queryDB()
        } catch (ex: Exception) {
            ex.printStackTrace()
            MatrixCursor(null)
        }
    }

    /**
     * Define the types that this CP will support.
     * By definition you can either interact with the entire Table or just
     * with an specific row.
     * If your URI ends with a number, that will define a row type.
     *
     */
    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            0 -> CP_MULTIPLE_RECORD_MIME_TYPE
            1 -> CP_SINGLE_RECORD_MIME_TYPE
            else -> throw Exception("Unsupported URI type")
        }
    }

    /**
     * Defines a INSERT INTO transaction.
     * In order to define the table name and columns with the values to be created,
     * Android utilize ContentValues data wrapper. It defines from which table and which columns
     * the values that will be used in this transaction.
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

}