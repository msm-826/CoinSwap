package com.project.coinswap.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.coinswap.data.local.entity.CurrencyRateEntity

@Database(
    entities = [CurrencyRateEntity::class],
    exportSchema = false,
    version = 1
)
abstract class CurrencyRateDatabase: RoomDatabase() {

    abstract val currencyRateDao: CurrencyRateDao
}