package br.com.coinconverter_dio.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.coinconverter_dio.data.dao.ExchangeDao
import br.com.coinconverter_dio.data.model.ExchangeResponseValue
import br.com.coinconverter_dio.utils.Constants.DATABASE

/**
 * @author RubioAlves
 * Created 11/08/2021 at 14:18
 */
@Database(entities = [ExchangeResponseValue::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exchangeDao():ExchangeDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE
            ).fallbackToDestructiveMigration().build()
        }
    }


}