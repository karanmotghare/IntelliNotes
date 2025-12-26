package com.example.intellinotes.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intellinotes.R
import com.example.intellinotes.domain.usecases.GetFolderUseCase
import com.example.intellinotes.ui.home.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFolders: GetFolderUseCase
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> =
        getFolders()
            .map { folders ->
                HomeUiState.Success(
                    folders = folders.map { it.toUiModel() }
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HomeUiState.Loading
            )

}