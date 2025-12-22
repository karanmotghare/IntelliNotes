package com.example.intellinotes.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intellinotes.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    val uiState: StateFlow<HomeUiState> =
        flowOf(
            HomeUiState.Success(
                folders = listOf(
                    FolderUiModel(
                        id = "Notes",
                        title = "Notes",
                        count = 5,
                        iconRes = R.drawable.yellow_folder
                    ),
                    FolderUiModel(
                        id = "RECENTLY_DELETED",
                        title = "Recently Deleted",
                        count = 2,
                        iconRes = R.drawable.yellow_bin
                    )
                )
            )
        ).stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Loading
        )
}