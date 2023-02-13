package ru.netology.Nmedia.postrepository

import androidx.lifecycle.map
import ru.netology.Nmedia.Post
import ru.netology.Nmedia.dao.PostDao
import ru.netology.Nmedia.entity.PostEntity


class PostRepositoryRoomImpl(
    private val dao: PostDao
) : PostRepository {

    override fun getAll() = dao.getAll().map { list -> list.map { it.toDto() } }

    override fun save(post: Post) = dao.save(PostEntity.fromDto(post))

    override fun likeById(id: Long) = dao.likeById(id)


    override fun sendMessage(id: Long) = dao.sendMessage(id)


    override fun removeById(id: Long) = dao.removeById(id)

}
