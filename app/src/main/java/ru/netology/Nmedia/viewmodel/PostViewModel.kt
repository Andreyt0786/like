package ru.netology.Nmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.Nmedia.Post
import ru.netology.Nmedia.postrepository.PostRepository
import ru.netology.Nmedia.postrepository.PostRepositoryInMemoryImp

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImp()
    val edited = MutableLiveData(empty)
    val data = repository.get()
    fun likeById(id: Long) = repository.likeById(id)
    fun sendMessage(id: Long) = repository.sendMessage(id)
    fun removeById(postId: Long) = repository.removeById(postId)
    fun save(post: Post) {
        edited.value?.let {
            repository.save(it)
            edited.value = empty
        }
    }

    fun changeContent(content: String) {
        if (content == edited.value?.content) {
            return
        }
        edited.value = edited.value?.copy(content = content)
    }
}

private val empty = Post(
    id = 0L,
    author = "",
    content = "",
    published = "",
    likeCount = 0,
    likedByMe = false,
    numberShare = 0
)