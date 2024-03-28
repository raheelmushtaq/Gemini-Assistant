package com.app.geminiassistant.di

import android.app.Application
import android.content.Context
import com.app.geminiassistant.datastore.DataStoreHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
* Di Module for Hilt*/
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideDataStoreHandle(@ApplicationContext context: Context): DataStoreHandler =
        DataStoreHandler(context)

}