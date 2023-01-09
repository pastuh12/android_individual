package com.example.individual.common

import android.app.Application
import android.content.Context
import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider

class App : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        DatabaseProvider.init(this)
        NetworkProvider.init(this)
    }

    companion object {
        private lateinit var instance: App
        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }
}