package com.jmdev.app.todo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.jmdev.app.todo.R
import com.jmdev.app.todo.ui.components.TaskCard
import com.jmdev.app.todo.ui.navigation.core.LocalNavigationController
import com.jmdev.app.todo.ui.viewmodel.AppViewModel

@Composable
fun History(
    modifier: Modifier = Modifier,
    navController: NavController = LocalNavigationController.current,
    appViewModel: AppViewModel
) {
    appViewModel.getCompletedTasks()
    val completedTasks by appViewModel.completedTasks.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.min_padding))
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = modifier
                .padding(bottom = dimensionResource(id = R.dimen.default_padding))
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(
                        id = R.string.go_back
                    )
                )
            }
            Text(text = stringResource(id = R.string.tasks_history))
        }
        if (completedTasks?.isNotEmpty() == true) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { appViewModel.deleteCompletedTasks() }) {
                    Icon(
                        imageVector = Icons.Filled.DeleteSweep,
                        contentDescription = stringResource(R.string.delete_history)
                    )
                }
                Text(
                    text = stringResource(id = R.string.delete_history), modifier = modifier.padding(
                        start = dimensionResource(
                            id = R.dimen.min_padding
                        )
                    )
                )
            }
            completedTasks?.forEach {
                TaskCard(task = it, appViewModel = appViewModel)
            }
        } else {
            Text(
                text = stringResource(R.string.no_completed_tasks_yet),
                modifier = modifier.padding(top = dimensionResource(id = R.dimen.default_padding))
            )
        }
    }
}