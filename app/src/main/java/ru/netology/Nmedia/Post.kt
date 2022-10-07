package ru.netology.Nmedia

import android.provider.MediaStore

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likeCount: Int,
    val likedByMe: Boolean = false,
    val numberShare: Int = 990,
    val video: String?=""
)

