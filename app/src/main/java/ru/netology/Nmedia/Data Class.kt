package ru.netology.Nmedia

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likeCount: Int,
    val likedByMe: Boolean = false,
    var numberShare: Int = 990
)