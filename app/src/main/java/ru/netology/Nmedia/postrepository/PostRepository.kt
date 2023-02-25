package ru.netology.Nmedia.postrepository


import androidx.lifecycle.LiveData
import ru.netology.Nmedia.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>> //   было так fun get(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun sendMessage(id: Long)
    fun removeById(id: Long) // fun removeById(postId: Long)
    fun save(post: Post)

}