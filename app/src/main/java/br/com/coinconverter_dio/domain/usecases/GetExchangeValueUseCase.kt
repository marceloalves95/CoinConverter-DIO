package br.com.coinconverter_dio.domain.usecases

import br.com.coinconverter_dio.core.others.UseCase
import br.com.coinconverter_dio.data.model.ExchangeResponse
import br.com.coinconverter_dio.data.model.ExchangeResponseValue
import br.com.coinconverter_dio.data.repository.CoinRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author RubioAlves
 * Created 11/08/2021 at 11:55
 */
class GetExchangeValueUseCase(private val repository:CoinRepository):UseCase<String, ExchangeResponseValue>() {
    override suspend fun execute(param: String): Flow<ExchangeResponseValue> {
        return repository.getExchangeValue(param)
    }
}