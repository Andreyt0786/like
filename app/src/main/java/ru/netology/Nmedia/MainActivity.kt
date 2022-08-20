package ru.netology.Nmedia

import Post
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.netology.Nmedia.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb\"",
            published = " 21 мая в 18:36",
            likedByMe = false,
            likeCount = 0
        )

        with(binding) {
            author.text = post.author
            content.text = post.content
            published.text = post.published
            numberLike?.text = countEveryThing(post.likeCount)
            numberShare?.text = countEveryThing(post.numberShare)
            love.setImageResource(
                if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_outline_favorite_border_24
            )

            love.setOnClickListener {
                Log.d("TAG", "like")
                post.likedByMe = !post.likedByMe
                love.setImageResource(
                    if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_outline_favorite_border_24
                )
                if (post.likedByMe) post.likeCount++ else post.likeCount--
                numberLike?.text = countEveryThing(post.likeCount)
            }

            sendMessage.setOnClickListener {
                post.numberShare++
                numberShare?.text = countEveryThing(post.numberShare)
            }
            root.setOnClickListener {
                Log.d("TAG", " stuff")
            }

            avatar.setOnClickListener {
                Log.d("TAG", " avatar")
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