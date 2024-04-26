package com.jmdev.app.todo.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jmdev.app.todo.R
import com.jmdev.app.todo.ui.components.CreateTaskBottomSheet
import com.jmdev.app.todo.ui.components.TaskCard
import com.jmdev.app.todo.ui.navigation.core.LocalNavigationController
import com.jmdev.app.todo.ui.navigation.core.Routes
import com.jmdev.app.todo.ui.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState", "MutableCollectionMutableState")
@Composable
fun Home(
    modifier: Modifier = Modifier,
    navController: NavController = LocalNavigationController.current,
    appViewModel: AppViewModel
) {
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        CreateTaskBottomSheet(appViewModel = appViewModel) {
            showSheet = false
        }
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val topBarColor = TopAppBarColors(
        scrolledContainerColor = MaterialTheme.colorScheme.background,
        containerColor = MaterialTheme.colorScheme.background,
        actionIconContentColor = MaterialTheme.colorScheme.onSurface,
        navigationIconContentColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.onSurface
    )
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontSize = dimensionResource(id = R.dimen.title_font_size).value.sp
                    )
                },
                actions = {
                    Row(modifier = modifier.width(dimensionResource(id = R.dimen.options_container_width))) {
                        IconButton(onClick = { showSheet = true }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = stringResource(R.string.add_new_task)
                            )
                        }
                        IconButton(
                            onClick = {
                                navController.navigate(Routes.History.route) {
                                    launchSingleTop = true
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.History,
                                contentDescription = stringResource(
                                    R.string.tasks_history
                                )
                            )
                        }
                        IconButton(onClick = {
                            navController.navigate(Routes.Info.route) {
                                launchSingleTop = true
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = stringResource(
                                    R.string.app_info
                                )
                            )
                        }
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = topBarColor
            )
        }
    ) {

        Column(
            Modifier
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding(),
                    start = dimensionResource(
                        id = R.dimen.default_padding
                    ),
                    end = dimensionResource(id = R.dimen.default_padding)
                )
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.task_separator_padding_top),
                        bottom = dimensionResource(id = R.dimen.default_padding)
                    )
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tasks", modifier = modifier.padding(
                        end = dimensionResource(id = R.dimen.min_padding)
                    )
                )
                HorizontalDivider()
            }
            val elements by appViewModel.tasks.collectAsState()
            if (elements?.isNotEmpty() == true) {
                elements?.forEach { model ->
                    TaskCard(task = model, appViewModel = appViewModel)
                }
            } else {
                Text(text = stringResource(R.string.no_tasks_created))
            }
        }
    }
}