package br.com.coinconverter_dio.data.dao

import androidx.room.*
import br.com.coinconverter_dio.data.model.ExchangeResponseValue
import kotlinx.coroutines.flow.Flow

/**
 * @author RubioAlves
 * Created 11/08/2021 at 14:28
 */
@Dao
interface ExchangeDao {

    @Query("SELECT * FROM tb_exchange")
    fun findAll():Flow<List<ExchangeResponseValue>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: ExchangeResponseValue)





}