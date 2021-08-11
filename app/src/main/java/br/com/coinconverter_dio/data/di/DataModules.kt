package br.com.coinconverter_dio.data.di

import android.util.Log
import br.com.coinconverter_dio.data.database.AppDatabase
import br.com.coinconverter_dio.data.repository.CoinRepository
import br.com.coinconverter_dio.data.repository.CoinRepositoryImpl
import br.com.coinconverter_dio.data.services.AwesomeService
import br.com.coinconverter_dio.utils.Constants.BASE_URL
import br.com.coinconverter_dio.utils.Constants.HTTP_TAG
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author RubioAlves
 * Created 11/08/2021 at 11:18
 */
object DataModules {

    fun load(){
        loadKoinModules(networkModule() + repositoryModule() + databaseModule())
    }

    private fun networkModule(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor{
                    Log.e(HTTP_TAG, ":$it")
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }
            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }
            single {
                createServices<AwesomeService>(get(), get())
            }
        }
    }
    private fun repositoryModule(): Module {
        return module {
            single<CoinRepository> { CoinRepositoryImpl(get(), get())}
        }
    }

    private fun databaseModule():Module{
        return module {
            single { AppDatabase.getInstance(androidApplication())}
        }
    }

    private inline fun <reified T>createServices(client: OkHttpClient, factory: GsonConverterFactory):T{

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(T::class.java)

    }
}