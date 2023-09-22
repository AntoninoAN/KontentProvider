package com.example.contentproviderkotlin.model.common

import android.net.Uri

const val DATABASE_NAME = "user_db"

const val CP_AUTHORITY = "com.example.contentproviderkotlin.provider"

const val CP_CONTENT_PATH = "users"

val CP_CONTENT_URI = Uri.parse("content://$CP_AUTHORITY/$CP_CONTENT_PATH")

const val CP_SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.$CP_AUTHORITY.$CP_CONTENT_PATH"
const val CP_MULTIPLE_RECORD_MIME_TYPE = "vnd.android.cursor.dir/vnd.$CP_AUTHORITY.$CP_CONTENT_PATH"