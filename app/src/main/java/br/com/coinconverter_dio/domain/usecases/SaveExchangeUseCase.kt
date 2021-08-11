package br.com.coinconverter_dio.domain.usecases

import br.com.coinconverter_dio.core.others.UseCase
import br.com.coinconverter_dio.data.model.ExchangeResponseValue
import br.com.coinconverter_dio.data.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author RubioAlves
 * Created 11/08/2021 at 14:38
 */
class SaveExchangeUseCase(private val repository: CoinRepository):UseCase.NoSource<ExchangeResponseValue>() {
    override suspend fun execute(param: ExchangeResponseValue): Flow<Unit> {
        return flow {
            repository.save(param)
            emit(Unit)
        }
    }
}