package com.jimmyh.mobilitylens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jimmyh.mobilitylens.ui.theme.MobilityLensTheme

data class MobilityDimension(
    val name: String,
    val description: String,
    val implication: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobilityLensTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MobilityLensScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun rememberMobilityDimensions(): List<MobilityDimension> {
    return listOf(
        MobilityDimension(
            stringResource(R.string.dimension_1_name),
            stringResource(R.string.dimension_1_description),
            stringResource(R.string.dimension_1_implication)
        ),
        MobilityDimension(
            stringResource(R.string.dimension_2_name),
            stringResource(R.string.dimension_2_description),
            stringResource(R.string.dimension_2_implication)
        ),
        MobilityDimension(
            stringResource(R.string.dimension_3_name),
            stringResource(R.string.dimension_3_description),
            stringResource(R.string.dimension_3_implication)
        ),
        MobilityDimension(
            stringResource(R.string.dimension_4_name),
            stringResource(R.string.dimension_4_description),
            stringResource(R.string.dimension_4_implication)
        ),
        MobilityDimension(
            stringResource(R.string.dimension_5_name),
            stringResource(R.string.dimension_5_description),
            stringResource(R.string.dimension_5_implication)
        ),
        MobilityDimension(
            stringResource(R.string.dimension_6_name),
            stringResource(R.string.dimension_6_description),
            stringResource(R.string.dimension_6_implication)
        )
    )
}

@Composable
fun MobilityLensScreen(modifier: Modifier = Modifier) {
    val dimensions = rememberMobilityDimensions()

    var currentIndex by remember { mutableIntStateOf(0) }
    var appName by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("") }

    val currentDimension = dimensions[currentIndex]
    val blankWarning = stringResource(R.string.blank_input_warning)
    val resultTemplate = stringResource(R.string.result_message)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.app_title),
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = stringResource(R.string.app_intro),
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = currentDimension.name,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = currentDimension.description,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = currentDimension.implication,
            style = MaterialTheme.typography.bodyMedium
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(22.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { if (currentIndex > 0) currentIndex-- },
                enabled = currentIndex > 0,
                modifier = Modifier.height(48.dp)
            ) {
                Text(stringResource(R.string.previous_button))
            }
            Button(
                onClick = { if (currentIndex < dimensions.lastIndex) currentIndex++ },
                enabled = currentIndex < dimensions.lastIndex,
                modifier = Modifier.height(48.dp)
            ) {
                Text(stringResource(R.string.next_button))
            }
        }

        OutlinedTextField(
            value = appName,
            onValueChange = { appName = it },
            label = { Text(stringResource(R.string.app_name_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                resultMessage = if (appName.isBlank()) {
                    blankWarning
                } else {
                    String.format(resultTemplate, appName, currentDimension.name)
                }
            }
        ) {
            Text(stringResource(R.string.check_button))
        }

        if (resultMessage.isNotBlank()) {
            Text(
                text = resultMessage,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true, device = "spec:width=1080px,height=2340px,dpi=440")
@Composable
fun MobilityLensScreenPreview() {
    MobilityLensTheme {
        MobilityLensScreen()
    }
}