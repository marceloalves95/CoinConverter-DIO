package br.com.coinconverter_dio.application

import android.app.Application
import br.com.coinconverter_dio.data.di.DataModules
import br.com.coinconverter_dio.domain.di.DomainModule
import br.com.coinconverter_dio.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @author RubioAlves
 * Created 11/08/2021 at 11:37
 */
class MainApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
        }

        DataModules.load()
        DomainModule.load()
        PresentationModule.load()
    }
}