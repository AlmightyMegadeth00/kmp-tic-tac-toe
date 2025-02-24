package org.kmptictactoe.project

import android.app.Application
import org.kmptictactoe.project.dependency_injection.initKoin
import org.koin.android.ext.koin.androidContext

class KoinApplication: Application() {
    companion object {
        private val TAG = KoinApplication::class.java.simpleName
    }

    override fun onCreate() {
        initKoin {
            androidContext(this@KoinApplication)
        }
        super.onCreate()
    }
}