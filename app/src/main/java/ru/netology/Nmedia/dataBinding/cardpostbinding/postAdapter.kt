package ru.netology.Nmedia.dataBinding.cardpostbinding

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
    private val onLikeListener: (Post) -> Unit,
    private val onSendMessageListener: (Post) -> Unit,
    private val onRemoveListener: (Post) -> Unit,

) : ListAdapter<Post, postViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postViewHolder =
        postViewHolder(
            CardpostBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onLikeListener = onLikeListener,
            onSendMessageListener = onSendMessageListener,
            onRemoveListener = onRemoveListener
        )

    override fun onBindViewHolder(holder: postViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}