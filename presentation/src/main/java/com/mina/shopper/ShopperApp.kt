package com.mina.shopper

import android.app.Application
import com.mina.domain.di.domainModule
import com.mina.data.di.dataModule
import com.mina.shopper.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShopperApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShopperApp)
            modules(
                listOf(
                presentationModule,
                dataModule,
                domainModule
            )
            )
        }
    }
}