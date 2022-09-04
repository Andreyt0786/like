package ru.netology.Nmedia

data class Post(
    var id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likeCount: Int,
    val likedByMe: Boolean = false,
    val numberShare: Int = 990
)