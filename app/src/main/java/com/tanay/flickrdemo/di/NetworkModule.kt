package com.tanay.flickrdemo.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tanay.flickrdemo.BuildConfig
import com.tanay.flickrdemo.data.remote.ApiHelper
import com.tanay.flickrdemo.utils.Urls
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {


    @Provides
    @Singleton
    internal fun provideHttpClient(logger: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        builder.retryOnConnectionFailure(true)
        builder.addInterceptor(logger)
        builder.cache(cache)
        builder.connectTimeout(60, TimeUnit.SECONDS)
        builder.readTimeout(60, TimeUnit.SECONDS)
        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    internal fun provideCache(file: File): Cache {
        return Cache(file, (10 * 1024 * 1024).toLong())
    }

    @Provides
    @Singleton
    internal fun provideCacheFile(context: Context): File {
        return context.filesDir
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideGsonClient(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    internal fun provideScalerClient(): ScalarsConverterFactory {
        return ScalarsConverterFactory.create()
    }

    @Provides
    @Singleton
    internal fun provideRxAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    internal fun provideApiService(
        client: OkHttpClient,
        scalarsConverterFactory: ScalarsConverterFactory,
        rxAdapter: RxJava2CallAdapterFactory
    ): ApiHelper {
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(Urls.BASE_URL)
            .addConverterFactory(scalarsConverterFactory)
            .addCallAdapterFactory(rxAdapter)
            .build()

        return retrofit.create(ApiHelper::class.java)
    }
}