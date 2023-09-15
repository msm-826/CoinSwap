package com.project.coinswap.presentation.mainscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.project.coinswap.R
import com.project.coinswap.domain.model.CurrencyRate

@Composable
fun BottomSheetContent(
    onItemClicked: (String) -> Unit,
    currenciesList: List<CurrencyRate>
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.padding_medium))
    ) {
        items(currenciesList) {currency ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClicked(currency.code) }
                    .padding(vertical = 4.dp, horizontal = 16.dp)
            ) {
                Text(text = stringResource(
                    R.string.bottom_sheet_currency,
                    currency.code,
                    currency.name
                ))
            }
        }
    }
}