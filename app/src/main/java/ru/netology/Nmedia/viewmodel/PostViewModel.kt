package ru.netology.Nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.Nmedia.Post
import ru.netology.Nmedia.postrepository.PostRepository
import ru.netology.Nmedia.postrepository.PostRepositoryFilesImp

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository = PostRepositoryFilesImp(application)
    val edited = MutableLiveData(empty)
    val data = repository.get()
    fun likeById(id: Long) = repository.likeById(id)
    fun sendMessage(id: Long) = repository.sendMessage(id)
    fun removeById(postId: Long) = repository.removeById(postId)


    fun changeContentAndSave(content: String) {
        val text = content.trim()
        if (text == edited.value?.content) {
            return
        }
        edited.value?.let {
            repository.save(it.copy(content = text))
        }
        edited.value = empty
    }


    fun edit(post: Post) {

        edited.value = post
    }

    fun cansel() {

        edited.value = edited.value?.copy(content = edited.value!!.content)
    }
}

private val empty = Post(
    id = 0L,
    author = "",
    content = "",
    published = "",
    likeCount = 0,
    likedByMe = false,
    numberShare = 0,
    video = ""
)