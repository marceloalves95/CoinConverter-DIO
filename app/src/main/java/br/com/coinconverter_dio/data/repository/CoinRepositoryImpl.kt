package br.com.coinconverter_dio.data.repository

import br.com.coinconverter_dio.core.others.RemoteException
import br.com.coinconverter_dio.data.database.AppDatabase
import br.com.coinconverter_dio.data.model.ErrorResponse
import br.com.coinconverter_dio.data.model.ExchangeResponseValue
import br.com.coinconverter_dio.data.services.AwesomeService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

/**
 * @author RubioAlves
 * Created 11/08/2021 at 11:48
 */
class CoinRepositoryImpl(
    appDatabase: AppDatabase,
    private val service: AwesomeService
    ) : CoinRepository {

    private val dao = appDatabase.exchangeDao()

    override suspend fun getExchangeValue(coins: String) = flow {

        try {
            val exchangeValue = service.exchangeValue(coins)
            val exchange = exchangeValue.values.first()
            emit(exchange)
        } catch (e: HttpException) {
            val json = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(json, ErrorResponse::class.java)
            throw RemoteException(errorResponse.message)
        }

    }

    override suspend fun save(exchange: ExchangeResponseValue) {
        dao.save(exchange)
    }

    override fun list(): Flow<List<ExchangeResponseValue>> {
        return dao.findAll()
    }
}