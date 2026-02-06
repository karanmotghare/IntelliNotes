package com.karan.intellinotes.domain.usecases

import com.karan.intellinotes.data.repository.repo.FolderRepository
import javax.inject.Inject

class GetFolderUseCase @Inject constructor(
    private val repository: FolderRepository
) {
    operator fun invoke() = repository.getFolders()
}