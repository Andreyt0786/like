package ru.netology.Nmedia.postrepository


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.Nmedia.Post


class PostRepositoryFilesImp(val context: Context) : PostRepository {
    private var nextId = 1L
    private val gson = Gson()
    private val filename = "posts.json"
    private val typeToken = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private var posts = emptyList<Post>()
            set(value) {
            field = value
            sync()
        }
    private val data = MutableLiveData(posts)

    init {
        val file = context.filesDir.resolve(filename)
        if (file.exists()) {
            context.openFileInput(filename).bufferedReader().use {
                posts = gson.fromJson(it, typeToken)
                nextId = (posts.maxOfOrNull { it.id } ?: 0) + 1
            }
        } else {
            posts = listOf(
                Post(
                    id = nextId++,
                    author = "Университет интернет-профессий будущего. Нетология. ",
                    content = "# Поста 2 Привет, это новая Нетология!",
                    published = " 21 мая в 18:36",
                    likedByMe = false,
                    likeCount = 0,
                    video = "https://www.youtube.com/watch?v=WhWc3b3KhnY"
                ),
                Post(
                    id = nextId++,
                    author = "Университет  ",
                    content = "пост под номером 2",
                    published = " 21 мая в 18:36",
                    likedByMe = false,
                    likeCount = 0,
                    video = ""
                ),
                Post(
                    id = nextId++,
                    author = "Нетология ",
                    content = "Еще один пост",
                    published = " 21 мая в 18:36",
                    likedByMe = false,
                    likeCount = 0,
                    video = null
                )
            )
            sync()
        }
        data.value = posts
    }





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
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = nextId++,
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
    private fun sync(){
        context.openFileOutput(filename,Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(posts))
        }
    }
}