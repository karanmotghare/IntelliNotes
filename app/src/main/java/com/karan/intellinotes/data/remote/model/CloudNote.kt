package com.karan.intellinotes.data.remote.model

data class CloudNote (
    val id: String = "",
    val title: String? = "",
    val content: String? = "",
    val folderId: String = "",
    val noteType: String = "TEXT",

    val isDeleted: Boolean = false,

    val createdAt: Long = 0L,
    val updatedAt: Long? = 0L,

    val version: Int = 1
)