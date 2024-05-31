package com.pr7.jc_mvi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pr7.jc_mvi.model.MainContract
import com.pr7.jc_mvi.model.MainViewModel
import com.pr7.jc_mvi.ui.theme.JC_MVITheme

class MainActivity : ComponentActivity() {

    val mainViewModel by viewModels<MainViewModel>()
    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JC_MVITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Column(modifier = Modifier.padding(innerPadding)) {
                       val state=mainViewModel.state.collectAsState().value
                       MainScreen(
                           uiState = state,
                           events =mainViewModel::onEventDispatcher
                       )
                   }
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    uiState: MainContract.UiState,
    events:(MainContract.MainIntent)->Unit
) {
    Text(text = "${uiState.count}")
    Button(onClick = {
        events.invoke(MainContract.MainIntent.buttonClick)
    }) {
        Text(text = "Go")
    }

}

