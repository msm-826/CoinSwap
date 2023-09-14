package com.project.coinswap.presentation.mainscreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.coinswap.R

@Composable
fun MainScreen(
    state: MainScreenState,
    onEvent: (MainScreenEvent) -> Unit
) {

    val keys = listOf(
        "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "C"
    )

    val context = LocalContext.current
    LaunchedEffect(key1 = state.error) {
        if (state.error != null) {
            Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_large),
                vertical = dimensionResource(id = R.dimen.padding_medium)
            ),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text( //title
            text = stringResource(R.string.coinswap),
            fontFamily = FontFamily.Cursive,
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            contentAlignment = Alignment.CenterStart,/*
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_very_large))*/
        ) {
            Column {
                CurrencyCard( //first currency card
                    currencyCode = state.fromCurrencyCode,
                    currencyName = state.currencyRates[state.fromCurrencyCode]?.name ?: "",
                    currencyValue = state.fromCurrencyValue,
                    color = if (state.selection == SelectionState.FROM) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    },
                    onDropdownIconClicked = { },
                    onCurrencySelect = { onEvent(MainScreenEvent.FromCurrencySelect) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier
                    .height(dimensionResource(id = R.dimen.padding_between_currency_card))
                )
                CurrencyCard( //second currency card
                    currencyCode = state.toCurrencyCode,
                    currencyName = state.currencyRates[state.toCurrencyCode]?.name ?: "",
                    currencyValue = state.toCurrencyValue,
                    color = if (state.selection == SelectionState.TO) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    },
                    onDropdownIconClicked = { },
                    onCurrencySelect = { onEvent(MainScreenEvent.ToCurrencySelect) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Box( //sync button
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.sync_icon_position))
                    .clip(CircleShape)
                    .clickable { onEvent(MainScreenEvent.SwapIconClicked) }
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sync),
                    contentDescription = stringResource(R.string.swap_currency),
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                        .size(25.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_between_keys))
        ) {
            items(keys) {key ->
                KeyboardButton(
                    key = key,
                    backgroundColor = when(key) {
                        "C" -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    onClick = {
                        onEvent(MainScreenEvent.NumberButtonClicked(key))
                    },
                    modifier = Modifier.aspectRatio(1f)
                )
            }
        }
    }
}

@Composable
fun CurrencyCard(
    currencyCode: String,
    currencyName: String,
    currencyValue: String,
    color: Color,
    onDropdownIconClicked: () -> Unit,
    onCurrencySelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.padding_large),
                    vertical = dimensionResource(id = R.dimen.padding_small)
                ),
            horizontalAlignment = Alignment.End
        ) {
            CurrencyLabel( //currency display details
                currencyCode = currencyCode,
                currencyName = currencyName,
                onDropdownIconClicked = onDropdownIconClicked,
                modifier = Modifier.fillMaxWidth()
            )
            Text( //amount aligned to the end
                text = currencyValue,
                fontSize = 40.sp,
                modifier = Modifier.clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = onCurrencySelect
                ),
                color = color
            )
        }
    }
}

@Composable
fun CurrencyLabel(
    currencyCode: String,
    currencyName: String,
    onDropdownIconClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = currencyCode,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = onDropdownIconClicked) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = stringResource(R.string.open_bottom_sheet)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = currencyName,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun KeyboardButton(
    key: String,
    backgroundColor: Color,
    onClick: (String) -> Unit,
    modifier: Modifier =Modifier
) {
    Box(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .clip(CircleShape)
            .background(color = backgroundColor)
            .clickable { onClick(key) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = key,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

/*
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    CoinSwapTheme {
        MainScreen(
            state = MainScreenState(),
            onEvent = MainScreenEvent
        )
    }
}*/
