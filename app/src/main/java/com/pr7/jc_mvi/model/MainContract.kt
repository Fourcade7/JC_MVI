package com.pr7.jc_mvi.model

import kotlinx.coroutines.flow.MutableStateFlow


interface MainContract {
    interface MainViewModel{

        var state:MutableStateFlow<UiState>
        fun onEventDispatcher(intent: MainIntent)

    }

    data class UiState(var count:Int)

     sealed interface MainIntent{
        object buttonClick:MainIntent
    }
}