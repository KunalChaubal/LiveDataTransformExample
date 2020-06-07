package com.transformations.sample

import android.app.Application
import com.transformations.sample.data.remote.example.fetchDetailsRemoteModule
import com.transformations.sample.data.remote.networkModule
import com.transformations.sample.ui.example.fetchDetailsModule
import com.transformations.sample.ui.main.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class
 */
class TransformationsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(
                mainModule, fetchDetailsModule, fetchDetailsRemoteModule,
                networkModule
            ))
        }
    }
}