package ru.netology.Nmedia.dataBinding.cardpostbinding

import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.Nmedia.Post
import ru.netology.Nmedia.R
import ru.netology.Nmedia.databinding.CardpostBinding


class postViewHolder(
    private val binding: CardpostBinding,
    private val listener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        with(binding) {
            author.text = post.author
            content.text = post.content
            published.text = post.published
            love.text = countEveryThing(post.likeCount)
            sendMessage.text = countEveryThing(post.numberShare)
            love.isChecked = post.likedByMe

            if (post.video.isNullOrBlank()) {
                shining.visibility = View.GONE
                play.visibility=View.GONE
            }

            love.setOnClickListener {
                listener.like(post)
            }
            // добавление новой функции для перехода в новый фрагмент
            content.setOnClickListener {
                listener.go(post)
            }

            shining.setOnClickListener {
                    listener.play(post)
                }


            sendMessage.setOnClickListener {
                listener.send(post)
            }

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.option_menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                listener.remove(post)
                                true
                            }
                            R.id.edit -> {
                                listener.edit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
    }

    fun countEveryThing(count: Int): String {
        var sum: String = ""
        when {
            count < 1000 -> sum = count.toString()
            count >= 1000 && count <= 1100 -> sum = "1,0K"
            count > 1100 && count <= 1200 -> sum = "1,1K"
            count > 1200 && count <= 1300 -> sum = "1,2K"
            count > 1300 && count <= 1400 -> sum = "1,3K"
            count > 1400 && count <= 1500 -> sum = "1,4K"
            count > 1500 && count <= 1600 -> sum = "1,5K"
            count > 1600 && count <= 1700 -> sum = "1,6K"
            count > 1700 && count <= 1800 -> sum = "1,7K"
            count > 1800 && count <= 1900 -> sum = "1,8K"
            count > 1900 && count < 10000 -> sum = "1,9K"
            count >= 10000 && count < 11000 -> sum = "10K"
            count > 11000 && count <= 12000 -> sum = "1,1M"
            count > 12000 && count <= 13000 -> sum = "1,2M"
            count > 13000 && count <= 14000 -> sum = "1,3M"
            count > 14000 && count <= 15000 -> sum = "1,4M"
            count > 15000 && count <= 16000 -> sum = "1,5M"
            count > 16000 && count <= 17000 -> sum = "1,6M"
            count > 17000 && count <= 18000 -> sum = "1,7M"
            count > 18000 && count <= 19000 -> sum = "1,8M"
            count > 19000 && count < 100000 -> sum = "1,9M"
            count >= 100000 -> sum = "10M"
        }
        return sum
    }

}