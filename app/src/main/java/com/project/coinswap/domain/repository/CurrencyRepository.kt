package com.project.coinswap.domain.repository

import com.project.coinswap.domain.model.CurrencyRate
import com.project.coinswap.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getCurrencyRatesList(): Flow<Resource<List<CurrencyRate>>>
}