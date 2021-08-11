package br.com.coinconverter_dio.domain.usecases

import br.com.coinconverter_dio.core.others.UseCase
import br.com.coinconverter_dio.data.model.ExchangeResponseValue
import br.com.coinconverter_dio.data.repository.CoinRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author RubioAlves
 * Created 11/08/2021 at 14:40
 */
class ListExchangeUseCase(private val repository: CoinRepository):UseCase.NoParam<List<ExchangeResponseValue>>() {
    override suspend fun execute(): Flow<List<ExchangeResponseValue>> {
        return repository.list()
    }
}