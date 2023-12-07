package com.example.contentproviderkotlin.consumer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contentproviderkotlin.R
import com.example.contentproviderkotlin.model.common.CP_CONTENT_URI
import com.example.contentproviderkotlin.model.common.ColumnSchema

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getDataFromCP() {
        // we are getting all data from the CP Table.
        val query = contentResolver.query(
            CP_CONTENT_URI,
            arrayOf(
                ColumnSchema.COLUMN_FIRST_NAME,
                ColumnSchema.COLUMN_LAST_NAME),
            null,
            null,
            null
        )
    }
}













