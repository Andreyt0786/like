package ru.netology.Nmedia.postrepository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.Nmedia.Post


class PostRepositoryInMemoryImp : PostRepository {
    private var posts = listOf(
        Post(
            id = 3,
            author = "Университет интернет-профессий будущего. Нетология. ",
            content = "# Поста 2 Привет, это новая Нетология!",
            published = " 21 мая в 18:36",
            likedByMe = false,
            likeCount = 0,
            video = "https://www.youtube.com/watch?v=WhWc3b3KhnY"
        ),
        Post(
            id = 2,
            author = "Университет  ",
            content = "пост под номером 2",
            published = " 21 мая в 18:36",
            likedByMe = false,
            likeCount = 0,
            video = ""
        ),
        Post(
            id = 1,
            author = "Нетология ",
            content = "Еще один пост",
            published = " 21 мая в 18:36",
            likedByMe = false,
            likeCount = 0,
            video = null
        )

        )

    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data
    override fun sendMessage(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(numberShare = post.numberShare + 1)
            } else {
                post
            }
        }
        data.value = posts
    }

    override fun removeById(postId: Long) {
        posts = posts.filter { it.id != postId }
        data.value = posts
    }

    override fun save(post: Post) {
        var nextId = posts.size
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = nextId++.toLong(),
                    author = "Я",
                    published = "Сейчас",
                    likedByMe = false,
                    likeCount = 0
                )

            ) + posts
            data.value = posts
            return
        }
        posts = posts.map {
            if (it.id == post.id) {
                it.copy(content = post.content)
            } else {
                it
            }
        }
        data.value = posts
    }


    override fun likeById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(
                    likedByMe = !post.likedByMe,
                    likeCount = if (!post.likedByMe) {
                        post.likeCount + 1
                    } else {
                        post.likeCount - 1
                    }
                )
            } else {
                post
            }
        }
        data.value = posts

    }
}