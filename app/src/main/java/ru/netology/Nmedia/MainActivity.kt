package ru.netology.Nmedia


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.Nmedia.ViewModel.PostViewModel
import ru.netology.Nmedia.dataBinding.CardPostBinding.PostAdapter
import ru.netology.Nmedia.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(
            OnSendMessageListener = {
                viewModel.sendMessage(it.id)
            },
            OnLikeListener = {
                viewModel.likeById(it.id)
            },

            )

        binding.lists.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }


}


