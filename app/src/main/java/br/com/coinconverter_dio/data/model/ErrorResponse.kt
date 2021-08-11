package br.com.coinconverter_dio.data.model

data class ErrorResponse(
    val code: String,
    val message: String,
    val status: Long
)