package com.jmdev.app.todo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jmdev.app.todo.R
import com.jmdev.app.todo.model.TaskModel
import com.jmdev.app.todo.ui.navigation.core.LocalNavigationController
import com.jmdev.app.todo.ui.viewmodel.AppViewModel

@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    navController: NavController = LocalNavigationController.current,
    task: TaskModel,
    appViewModel: AppViewModel
) {
    Card(
        modifier = modifier
            .padding(
                top = dimensionResource(id = R.dimen.min_padding),
                bottom = dimensionResource(id = R.dimen.min_padding)
            )
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.min_padding))
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier
                    .wrapContentHeight()
                    .width(dimensionResource(id = R.dimen.info_container_max_width))
            ) {
                Text(
                    text = task.taskName,
                    modifier = modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.task_name_padding_top),
                            bottom = dimensionResource(
                                id = R.dimen.task_name_padding_bottom
                            )
                        ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = stringResource(R.string.created_at, task.createdAt),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = dimensionResource(id = R.dimen.task_created_at_font_size).value.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = modifier
                )
            }
            Box(
                modifier = modifier
                    .padding(dimensionResource(id = R.dimen.min_padding))
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Button(
                        onClick = {
                            if (task.taskState) {
                                appViewModel.deleteTask(task.id)
                            } else {
                                appViewModel.setCompleted(task.id)
                            }
                        }
                    ) {
                        if (task.taskState) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete from history"
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = stringResource(R.string.set_as_completed)
                            )
                        }
                    }
                    if (!task.taskState) {
                        Button(
                            onClick = {
                                appViewModel.deleteTask(task.id)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete from history"
                            )
                        }
                    }
                }

            }
        }

    }
}