package com.example.piggy.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.Moneybox
import com.example.piggy.R

@Composable
fun PiggyItem(
    moneybox: Moneybox
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(88.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF4A148C),
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = moneybox.title,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .align(Alignment.TopStart)
            )
            Text(
                text = stringResource(R.string.to, moneybox.dateto),
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(end = 6.dp)
                    .align(Alignment.TopEnd)
            )
            Text(
                text = "${moneybox.alreadyhave} ${moneybox.unit}",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .align(Alignment.BottomStart)
            )
            Text(
                text = "${moneybox.amount} ${moneybox.unit}",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(end = 6.dp)
                    .align(Alignment.BottomEnd)
            )
            LinearProgressIndicator(
                progress = { progress(moneybox.amount, moneybox.alreadyhave) },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 6.dp),
                color = Color.Green,
                trackColor = Color(0xFFE4E3DB)
            )
        }
    }
}

fun progress(maxValue: Int, currentValue: Int): Float {
    val progress = currentValue.toFloat() / maxValue.toFloat()
    return progress
}