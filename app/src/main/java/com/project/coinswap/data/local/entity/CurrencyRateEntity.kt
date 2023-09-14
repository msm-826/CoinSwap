package com.project.coinswap.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyRateEntity(
    @PrimaryKey @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "rate") val rate: Double
)
