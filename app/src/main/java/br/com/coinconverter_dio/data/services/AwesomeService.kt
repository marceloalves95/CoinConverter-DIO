package br.com.coinconverter_dio.data.services

import br.com.coinconverter_dio.data.model.ExchangeResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author RubioAlves
 * Created 11/08/2021 at 11:33
 */
interface AwesomeService {

    @GET("/json/last/{coins}")
    suspend fun exchangeValue(@Path("coins") coins:String):ExchangeResponse
}