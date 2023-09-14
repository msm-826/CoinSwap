package com.project.coinswap.data.local.entity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CurrencyRateEntity::class],
    version = 1
)
abstract class CurrencyRateDatabase: RoomDatabase() {

    abstract val currencyRateDao: CurrencyRateDao
}