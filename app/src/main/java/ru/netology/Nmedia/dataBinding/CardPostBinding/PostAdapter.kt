package ru.netology.Nmedia.dataBinding.CardPostBinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.netology.Nmedia.Post
import ru.netology.Nmedia.databinding.CardpostBinding

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
}

class PostAdapter(
    private val OnLikeListener: (Post) -> Unit,
    private val OnSendMessageListener: (Post) -> Unit,

) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(
            CardpostBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            OnLikeListener = OnLikeListener,
            OnSendMessageListener = OnSendMessageListener,
        )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}