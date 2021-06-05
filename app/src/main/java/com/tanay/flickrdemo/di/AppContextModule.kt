package com.tanay.flickrdemo.di

import android.content.Context
import com.tanay.flickrdemo.App
import com.tanay.flickrdemo.data.AppDataManager
import com.tanay.flickrdemo.data.DataManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppContextModule {

    @Provides
    internal fun provideContext(app: App): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }
}