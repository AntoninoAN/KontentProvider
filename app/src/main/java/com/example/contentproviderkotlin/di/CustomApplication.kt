package com.example.contentproviderkotlin.di

import android.app.Application
import android.content.Context

class CustomApplication: Application() {

    companion object {
        var customApplication: Context? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        customApplication = applicationContext
    }
}