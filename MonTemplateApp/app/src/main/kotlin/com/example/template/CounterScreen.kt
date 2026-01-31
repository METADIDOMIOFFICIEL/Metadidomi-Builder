package com.example.template

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CounterScreen(
    onGoToSettings: () -> Unit,
    viewModel: CounterViewModel = viewModel()
) {
    val count by viewModel.count

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Template App",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Le compte est : $count",
            style = MaterialTheme.typography.displayMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { viewModel.increment() }) {
            Text(" +1 ")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.decrement() }) {
            Text(" -1 ")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = onGoToSettings) {
            Text("Paramètres")
        }
    }
}