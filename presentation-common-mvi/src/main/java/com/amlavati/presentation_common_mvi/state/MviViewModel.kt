package com.amlavati.presentation_common_mvi.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amlavati.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<State : UiState, Action : UiAction, Event : NavigationEvent> :
    ViewModel() {

    // initial state of the view
    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    //view state which will be observed
    private val _viewState = MutableStateFlow(initialState)
    val viewState
        get() = _viewState.asStateFlow()

    // current state
    val currentState: State
        get() = viewState.value

    //   event/intent/action which will be observed
    private val _action = MutableSharedFlow<Action>()
    private val action
        get() = _action.asSharedFlow()

    // navigation event
    private val _navigationEvent = Channel<Event>()
    val navigationEvent
        get() = _navigationEvent.receiveAsFlow()

    // observe action from view
    init {
        viewModelScope.launch {
            action.collect {
                handleAction(it)
            }
        }
    }

    abstract fun handleAction(action: Action)

    // perform new user intent
    fun performAction(newAction: Action) {
        viewModelScope.launch {
            _action.emit(newAction)
        }
    }

    //    submit new state
    protected fun submitUiState(newState: State) {
        _viewState.value = newState
    }

    //
    protected fun sendNavigationEvent(event: Event) {
        viewModelScope.launch {
            _navigationEvent.send(event)
        }
    }

    protected fun emitUiState(newState: State.() -> State) {
        val state = currentState.newState()
        _viewState.value = state
    }

    protected fun submitNavigationEvent(event: () -> Event) {
        val navigationEvent = event()
        viewModelScope.launch {
            _navigationEvent.send(navigationEvent)
        }
    }

}