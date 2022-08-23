package ru.netology.Nmedia


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import ru.netology.Nmedia.ViewModel.PostViewModel
import ru.netology.Nmedia.databinding.ActivityMainBinding
import kotlin.properties.ReadOnlyProperty


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                content.text = post.content
                published.text = post.published
                numberLike.text = countEveryThing(post.likeCount)
                numberShare.text = countEveryThing(post.numberShare)
                love.setImageResource(
                    if (post.likedByMe) R.drawable.ic_baseline_favorite_24
                    else R.drawable.ic_outline_favorite_border_24
                )
            }
            binding.love.setOnClickListener {
                viewModel.like()
            }

            binding.sendMessage.setOnClickListener {
                viewModel.sendMessage()
            }
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
