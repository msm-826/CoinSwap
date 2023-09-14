package com.project.coinswap.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.project.coinswap.data.local.entity.CurrencyRateEntity

@Dao
interface CurrencyRateDao {

    @Upsert
    suspend fun upsertAll(currencyRates: List<CurrencyRateEntity>)

    @Query("SELECT * FROM currency")
    suspend fun getAllCurrencyRates(): List<CurrencyRateEntity>
}