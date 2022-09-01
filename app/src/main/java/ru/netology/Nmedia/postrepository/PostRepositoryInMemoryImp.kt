package ru.netology.Nmedia.postrepository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.Nmedia.Post


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
        if (post.id == 0L) {
            posts = listOf(
                post.copy(id = posts.firstOrNull()?.id ?: 1L)
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

        /*posts = posts.map { post ->
            if (post.id == id) {
                if (post.likedByMe) post.likeCount++ else post.likeCount--
                post.copy(likeCount = post.likeCount)
            } else {
                post
            }
        }
        data.value = posts*/
    }

}