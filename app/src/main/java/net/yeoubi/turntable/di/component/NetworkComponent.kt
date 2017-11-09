package net.yeoubi.turntable.di.component

import com.google.gson.Gson
import dagger.Component
import net.yeoubi.turntable.di.module.NetworkModule
import net.yeoubi.turntable.viewmodel.MainViewModel
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface NetworkComponent {

    fun provideRetrofit(): Retrofit
    fun provideGson(): Gson

    fun inject(viewModel: MainViewModel)
}
