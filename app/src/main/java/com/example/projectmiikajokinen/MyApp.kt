package com.example.projectmiikajokinen

import  android.app.Application
import android.content.Context
import android.util.Log
class MyApp: Application(){
    companion object{
        lateinit var appContext: Context
    }
    override fun onCreate() {
        super.onCreate()
        Log.d("000", "MyApp onCreate()")
        appContext = applicationContext
    }
}

