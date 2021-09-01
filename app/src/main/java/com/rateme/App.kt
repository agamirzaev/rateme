package com.rateme

import android.app.Application
import com.rateme.data.DataManager

class App : Application() {
    var dataManager: DataManager? = null
    override fun onCreate() {
        super.onCreate()
        dataManager = DataManager()
    }
}