package br.com.coinconverter_dio.domain.di

import br.com.coinconverter_dio.domain.usecases.GetExchangeValueUseCase
import br.com.coinconverter_dio.domain.usecases.ListExchangeUseCase
import br.com.coinconverter_dio.domain.usecases.SaveExchangeUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * @author RubioAlves
 * Created 11/08/2021 at 12:11
 */
object DomainModule {

    fun load(){
        loadKoinModules(useCaseModules())
    }

    private fun useCaseModules(): Module {
        return module {
            factory { ListExchangeUseCase(get())}
            factory { SaveExchangeUseCase(get())}
            factory { GetExchangeValueUseCase(get())}
        }
    }
}