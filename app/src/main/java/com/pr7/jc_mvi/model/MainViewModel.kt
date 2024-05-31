package com.pr7.jc_mvi.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow


class MainViewModel:ViewModel(),MainContract.MainViewModel{

    override var state: MutableStateFlow<MainContract.UiState> = MutableStateFlow(MainContract.UiState(0))


    override fun onEventDispatcher(intent: MainContract.MainIntent) {
            when(intent){
                MainContract.MainIntent.buttonClick->{
                    reduce {
                        it.copy(it.count.plus(1))
                    }

                }
            }
    }


    private fun reduce(block:(oldstate:MainContract.UiState)->MainContract.UiState){
        val old=state.value
        state.value=block(old)
    }
}