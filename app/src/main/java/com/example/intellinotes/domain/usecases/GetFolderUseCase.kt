package com.example.intellinotes.domain.usecases

import com.example.intellinotes.data.repository.repo.FolderRepository
import javax.inject.Inject

class GetFolderUseCase @Inject constructor(
    private val repository: FolderRepository
) {
    operator fun invoke() = repository.getFolders()
}