package com.example.piggy.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.piggy.R
import com.example.piggy.viewmodel.DbViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navController: NavController,
    dbViewModel: DbViewModel = koinViewModel()
) {
    var showDatePicker by remember {
        mutableStateOf(false)
    }
    var date by remember {
        mutableStateOf("Не определена")
    }
    var text by remember {
        mutableStateOf("")
    }
    var text2 by remember {
        mutableStateOf("")
    }
    var text3 by remember {
        mutableStateOf("")
    }
    var isChecked by remember {
        mutableStateOf(false)
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF4A148C)
            ),
            modifier = Modifier
                .statusBarsPadding()
                .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)),
            title = {
                Row {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                        Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Text(
                        text = stringResource(R.string.money_box),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .wrapContentSize(Alignment.CenterStart),
                        color = Color.White
                    )
                    IconButton(
                        onClick = {
                            dbViewModel.upsert(
                                title = text,
                                goalSum = text2.toInt(),
                                alreadyHave = if (text3.isEmpty()) 0 else text3.toInt(),
                                date = if (date == "Не определена") "" else "до $date"
                            )
                            navController.navigate("mainScreen")
                        },
                        Modifier
                            .align(Alignment.CenterVertically),
                        enabled = if (isChecked) {
                            text.isNotEmpty()
                                    && text2.isNotEmpty()
                                    && text3.isNotEmpty()
                                    && text2.matches(Regex("[0-9]+"))
                                    && text3.matches(Regex("[0-9]+"))
                        } else {
                            text.isNotEmpty()
                                    && text2.isNotEmpty()
                                    && text2.matches(Regex("[0-9]+"))
                        }
                    ) {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.goal),
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF4A148C),
                    unfocusedBorderColor = Color(0xFF4A148C)
                ),
                label = {
                    Text(
                        text = stringResource(R.string.flat),
                        color = Color(0xFFC7C7D8),
                        fontSize = 16.sp
                    )
                },
            )
            Text(
                text = stringResource(R.string.name),
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 25.dp)
            )
            Text(
                text = stringResource(R.string.sum),
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                value = text2,
                onValueChange = { text2 = it },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF4A148C),
                    unfocusedBorderColor = Color(0xFF4A148C)
                ),
                label = {
                    Text(
                        text = stringResource(R.string._50_000),
                        color = Color(0xFFC7C7D8),
                        fontSize = 16.sp
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Text(
                text = stringResource(R.string.plan),
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 25.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = !isChecked
                        expanded = !expanded
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF4A148C)
                    )
                )
                Text(
                    text = stringResource(R.string.already),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = 16.sp
                )
            }

            AnimatedVisibility(visible = expanded) {
                Column {
                    Text(
                        text = stringResource(R.string.sum2),
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    OutlinedTextField(
                        value = text3,
                        onValueChange = { text3 = it },
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF4A148C),
                            unfocusedBorderColor = Color(0xFF4A148C)
                        ),
                        label = {
                            Text(
                                text = stringResource(R.string._50_000),
                                color = Color(0xFFC7C7D8),
                                fontSize = 16.sp
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                    Text(
                        text = stringResource(R.string.sum3),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 25.dp)
                    )
                }
            }
            Text(
                text = stringResource(R.string.date),
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showDatePicker = true
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img), contentDescription = null,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .height(24.dp)
                        .width(24.dp),
                )
                Text(
                    text = date,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterVertically),
                    fontSize = 14.sp,
                    color = Color(0xFF4A148C)
                )
            }
            Text(
                text = stringResource(R.string.choose),
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
        if (showDatePicker) {
            val datePickerState = rememberDatePickerState()
            val confirmEnabled = remember {
                derivedStateOf { datePickerState.selectedDateMillis != null }
            }
            DatePickerDialog(
                onDismissRequest = {
                    showDatePicker = false
                },
                colors = DatePickerDefaults.colors(
                    containerColor = Color.White,
                ),
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDatePicker = false
                            val format = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
                            val formattedDate2 = format.format(datePickerState.selectedDateMillis)
                            date = formattedDate2
                        },
                        enabled = confirmEnabled.value
                    ) {
                        Text(stringResource(R.string.choose2))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDatePicker = false
                        }
                    ) {
                        Text(stringResource(R.string.cancel))
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState, colors = DatePickerDefaults.colors(
                        selectedDayContainerColor = Color(0xFF4A148C),
                    )
                )
            }
        }
    }
}