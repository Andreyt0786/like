package ru.netology.Nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.Nmedia.Post

@Entity
data class PostEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likeCount: Int,
    val likedByMe: Boolean = false,
    val numberShare: Int = 990,
    val video: String? = ""
){
    fun toDto()= Post(
        id=id,
        author= author,
        content=content,
        published=published,
        likeCount=likeCount,
        likedByMe,
        numberShare,
        video=video)

    companion object{
        fun fromDto(post: Post)=PostEntity(
            id = post.id,
            author = post.author,
            content = post.content,
            published = post.published,
            likeCount = post.likeCount,
            likedByMe = post.likedByMe,
            numberShare = post.numberShare,
            video = post.video
        )
    }

}