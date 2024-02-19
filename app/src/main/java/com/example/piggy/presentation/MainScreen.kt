package com.example.piggy.presentation

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.data.states.States
import com.example.domain.entity.Entity
import com.example.domain.model.MoneyBoxFromDb
import com.example.domain.model.Moneybox
import com.example.piggy.R
import com.example.piggy.viewmodel.DbViewModel
import com.example.piggy.viewmodel.NetworkViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    networkViewModel: NetworkViewModel = koinViewModel(),
    modifier: Modifier,
    navController: NavController,
    dbViewModel: DbViewModel = koinViewModel()
) {
    val state = networkViewModel.state.collectAsState()
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(stringResource(R.string.active), stringResource(R.string.archive))
    val scope = rememberCoroutineScope()

    val list = mutableListOf<Entity>()

    val dbList = dbViewModel.list.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
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
                            onClick = {},
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
                            onClick = {},
                            Modifier
                                .align(Alignment.CenterVertically)
                        ) {
                            Icon(
                                Icons.Filled.Menu,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
            )

            when (val currentState = state.value) {
                is States.Error -> {
                    currentState.error?.let {
                        Text(
                            text = it, modifier = modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                States.Loading -> {
                    CircularProgressIndicator(
                        modifier = modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }

                is States.Success -> {
                    TabRow(
                        modifier = Modifier.padding(top = 6.dp, start = 16.dp, end = 16.dp),
                        selectedTabIndex = tabIndex,
                        divider = {},
                        indicator = {
                            TabRowDefaults.SecondaryIndicator(
                                color = Color.Transparent
                            )
                        },
                    ) {
                        tabs.forEachIndexed { index, title ->
                            val color = remember {
                                Animatable(Color.DarkGray)
                            }
                            scope.launch {
                                color.animateTo(
                                    if (tabIndex == index) {
                                        Color.White
                                    } else {
                                        Color.White
                                    }
                                )
                            }
                            val tabShape = RoundedCornerShape(5.dp)

                            val tabModifier = Modifier
                                .background(color = color.value, shape = tabShape)
                                .height(40.dp)
                            Row(
                                modifier = Modifier.padding(horizontal = 3.dp)
                            ) {
                                Tab(
                                    text = {
                                        Row {
                                            Text(
                                                title,
                                                style = if (tabIndex == index) {
                                                    TextStyle(
                                                        color = Color.Black,
                                                        fontSize = 16.sp
                                                    )
                                                } else {
                                                    TextStyle(
                                                        color = Color(0xFFC7C7D8),
                                                        fontSize = 16.sp
                                                    )
                                                }
                                            )
                                        }
                                    },
                                    selected = tabIndex == index,
                                    onClick = {
                                        tabIndex = index
                                    },
                                    modifier = tabModifier
                                )
                            }
                        }
                    }
                    if (currentState.list.isNotEmpty()) {
                        list.addAll(currentState.list)
                    }
                    if (dbList.value.isNotEmpty()) {
                        list.addAll(dbList.value)
                    }

                    when (tabIndex) {
                        0 -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                items(list) { content ->
                                    when (content) {
                                        is Moneybox -> {
                                            PiggyItem(moneybox = content)
                                        }

                                        is MoneyBoxFromDb -> {
                                            FromDbItem(moneyBoxFromDb = content)
                                        }
                                    }
                                }
                                item {
                                    Spacer(modifier = Modifier.height(95.dp))
                                }
                            }
                        }

                        1 -> {}
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = {
                navController.navigate("addScreen")
            },
            modifier = modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xFF7E57C2),
            contentColor = Color.White
        ) {
            Icon(
                Icons.Filled.Add, "Floating action button.",
                modifier = Modifier.size(50.dp)
            )
        }
    }
}