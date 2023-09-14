package com.project.coinswap.data.remote

import com.project.coinswap.data.remote.dto.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("v1/latest")
    suspend fun getLatestRates(
        @Query("apikey") apiKey: String = API_KEY
    ): CurrencyDto

    companion object {
        const val API_KEY = "fca_live_yxByXpBUgwSkd47TukOfyKwepoVbonVqt6THYe4F"
        const val BASE_URL = "https://api.freecurrencyapi.com/"
    }

}