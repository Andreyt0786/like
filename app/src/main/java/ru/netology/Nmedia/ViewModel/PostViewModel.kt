package ru.netology.Nmedia.ViewModel

import androidx.lifecycle.ViewModel
import ru.netology.Nmedia.PostRepository.PostRepository
import ru.netology.Nmedia.PostRepository.PostRepositoryInMemoryImp

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImp()
    val data = repository.get()
    fun like() = repository.like()
    fun sendMessage() = repository.sendMessage()
}