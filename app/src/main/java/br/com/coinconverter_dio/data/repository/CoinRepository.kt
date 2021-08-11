package br.com.coinconverter_dio.data.repository

import br.com.coinconverter_dio.data.model.ExchangeResponseValue
import kotlinx.coroutines.flow.Flow

/**
 * @author RubioAlves
 * Created 11/08/2021 at 11:47
 */
interface CoinRepository {

    suspend fun getExchangeValue(coins:String):Flow<ExchangeResponseValue>
    suspend fun save(exchange: ExchangeResponseValue)
    fun list():Flow<List<ExchangeResponseValue>>

}