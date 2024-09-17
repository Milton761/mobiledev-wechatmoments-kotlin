package com.tws.moments.app

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tws.moments.data.source.api.profile.ProfileService
import com.tws.moments.data.source.api.tweet.TweetsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.ref.WeakReference
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesWeakContext(@ApplicationContext appContext: Context): WeakReference<Context> {
        return WeakReference(appContext)
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(): Retrofit {

        val baseUrl = "http://10.0.2.2:2727/"

        val client = OkHttpClient.Builder()
//            .addInterceptor(ChuckerInterceptor(TWApplication.app))
            .retryOnConnectionFailure(true)
            .build()

        val gson: Gson = GsonBuilder()
            .enableComplexMapKeySerialization()
//            .registerTypeAdapter(object : TypeToken<List<TweetEntity>>() {}.type, TweetDeserializer())
            .setPrettyPrinting()
            .setVersion(1.0)
            .create()

//        val gson = GsonBuilder()
//            .registerTypeAdapter(List::class.java, ItemsTypeAdapter())
//            .create()


        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()


    }

    @Singleton
    @Provides
    fun providesProfileService(retrofit: Retrofit): ProfileService {
        return retrofit.create(ProfileService::class.java)
    }

    @Singleton
    @Provides
    fun providesTweetsService(retrofit: Retrofit): TweetsService {
        return retrofit.create(TweetsService::class.java)
    }

    @Singleton
    @Provides
    fun providesDispatcher(): DispatcherAgent {
        return DispatcherAgent()
    }
}