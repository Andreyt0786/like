package ru.netology.Nmedia.PostRepository


import androidx.lifecycle.LiveData
import ru.netology.Nmedia.Post

interface PostRepository{
    fun get(): LiveData<List<Post>>
    fun likeById(id:Long)
    fun sendMessage(id: Long)
}