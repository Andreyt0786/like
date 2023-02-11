package ru.netology.Nmedia.dao
import ru.netology.Nmedia.Post

interface PostDao {
    fun getAll(): List<Post>
    fun save(post: Post): Post
    fun likeById(id: Long)
    fun removeById(id: Long)
    fun sendMessage(id: Long)
}
