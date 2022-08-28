package ru.netology.Nmedia.PostRepository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.Nmedia.Post
import ru.netology.Nmedia.R


class PostRepositoryInMemoryImp : PostRepository {
    private var posts = List(500) {
        Post(
            id = it.toLong(),
            author = "Университет интернет-профессий будущего. Нетология. ",
            content = "# Поста $it Привет, это новая Нетология!",
            published = " 21 мая в 18:36",
            likedByMe = false,
            likeCount = 0
        )
    }

    private val data = MutableLiveData(posts)
    override fun get(): LiveData<List<Post>> = data
    override fun sendMessage(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.numberShare++
                post.copy(numberShare = post.numberShare)
            } else {
                post
            }
        }
        data.value = posts
    }

    override fun likeById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(likedByMe = !post.likedByMe)
                // if (post.likedByMe) post.likeCount++ else post.likeCount--
                //  post.copy(likeCount = post.likeCount)
                // почему то не хотели добавляться likes... выходило черте что... а когда отдельно сделал likes
                // все начало работать, хотя в предыдущей версии все работало
            } else {
                post
            }
        }
        data.value = posts

        posts = posts.map { post ->
            if (post.id == id) {
                if (post.likedByMe) post.likeCount++ else post.likeCount--
                post.copy(likeCount = post.likeCount)
            } else {
                post
            }
        }
        data.value = posts
    }
}