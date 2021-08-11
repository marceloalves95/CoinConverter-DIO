package br.com.coinconverter_dio.presentation.di

import br.com.coinconverter_dio.presentation.viewmodel.HistoryViewModel
import br.com.coinconverter_dio.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * @author RubioAlves
 * Created 11/08/2021 at 12:23
 */
object PresentationModule {

    fun load(){
        loadKoinModules(viewModelModules())
    }

    private fun viewModelModules(): Module {
        return module {
            viewModel { MainViewModel(get(), get()) }
            viewModel { HistoryViewModel(get()) }
        }
    }
}