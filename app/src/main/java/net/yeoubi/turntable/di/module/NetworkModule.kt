package net.yeoubi.turntable.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import net.yeoubi.turntable.BuildConfig
import net.yeoubi.turntable.common.utils.RxThreadCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * InJung Chung
 */

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val builder = GsonBuilder()

        builder.setDateFormat("yyyy-MM-dd HH:mm:ss")
        builder.setLenient()

        return builder.create()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)
        builder.connectTimeout(30, TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxThreadCallAdapterFactory.create())
            .build()
    }
}
