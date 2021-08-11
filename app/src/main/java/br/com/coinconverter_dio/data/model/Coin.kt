package br.com.coinconverter_dio.data.model

import java.util.*

/**
 * @author RubioAlves
 * Created 11/08/2021 at 10:43
 */
enum class Coin(val locale: Locale){
    USD(Locale.US),
    CAD(Locale.CANADA),
    BRL(Locale("pt", "BR")),
    ARS(Locale("es","AR"));

    companion object{
        fun getByName(name:String) = values().find{ it.name == name} ?: BRL
    }
}