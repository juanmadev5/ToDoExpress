package com.jmdev.app.todo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.sp
import com.jmdev.app.todo.R
import com.jmdev.app.todo.ui.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskBottomSheet(appViewModel: AppViewModel, onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        BottomSheetContent(
            appViewModel = appViewModel,
            onDismiss = {
                onDismiss()
            }
        )
    }
}

@Composable
fun BottomSheetContent(
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel,
    onDismiss: () -> Unit
) {
    var taskName by remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.default_padding))
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        OutlinedTextField(
            value = taskName, onValueChange = { taskName = it },
            label = {
                Text(text = stringResource(R.string.add_task_here))
            },
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.TaskAlt,
                    contentDescription = stringResource(R.string.task)
                )
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.Sentences
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = Color.Transparent,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary
            ),
            textStyle = TextStyle(
                fontSize = dimensionResource(id = R.dimen.task_font_size).value.sp
            )
        )
        Row(
            modifier = modifier
                .padding(bottom = dimensionResource(id = R.dimen.save_task_button_padding_top))
                .fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    if (taskName.isNotEmpty()) {
                        appViewModel.createTask(taskName)
                    }
                    onDismiss()
                }
            ) {
                Text(text = stringResource(R.string.save_task))
            }
        }
    }
}