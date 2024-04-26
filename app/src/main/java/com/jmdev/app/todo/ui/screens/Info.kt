package com.jmdev.app.todo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jmdev.app.todo.R
import com.jmdev.app.todo.ui.navigation.core.LocalNavigationController

@Composable
fun Info(
    modifier: Modifier = Modifier,
    navController: NavController = LocalNavigationController.current
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.min_padding))
            .fillMaxSize()
    ) {
        Row(
            modifier = modifier
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
            Text(
                text = "App info",
                modifier = modifier.padding(start = dimensionResource(id = R.dimen.min_padding))
            )
        }
        Text(
            text = stringResource(id = R.string.app_name), modifier = modifier.padding(
                top = 32.dp
            ), fontSize = 24.sp
        )
        Text(
            text = "v1.0",
            modifier = modifier.padding(top = dimensionResource(id = R.dimen.min_padding))
        )
        Text(
            text = "by JM Dev",
            modifier = modifier.padding(top = dimensionResource(id = R.dimen.min_padding))
        )
        Text(
            text = stringResource(R.string.with_from_paraguay), modifier = modifier.padding(
                top = dimensionResource(
                    id = R.dimen.min_padding
                )
            )
        )
    }
}