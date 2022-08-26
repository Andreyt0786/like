package ru.netology.Nmedia.PostRepository


import androidx.lifecycle.LiveData
import ru.netology.Nmedia.Post

interface PostRepository{
    fun get(): LiveData<Post>
    fun like()
    fun sendMessage()
}