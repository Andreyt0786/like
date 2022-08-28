package ru.netology.Nmedia.ViewModel

import androidx.lifecycle.ViewModel
import ru.netology.Nmedia.PostRepository.PostRepository
import ru.netology.Nmedia.PostRepository.PostRepositoryInMemoryImp

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImp()
    val data = repository.get()
    fun likeById(id:Long) = repository.likeById(id)
    fun sendMessage(id: Long) = repository.sendMessage(id)
}