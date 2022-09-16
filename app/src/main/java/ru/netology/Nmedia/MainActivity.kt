package ru.netology.Nmedia


import android.R
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.Nmedia.Utils.AndroidUtils
import ru.netology.Nmedia.dataBinding.cardpostbinding.OnInteractionListener
import ru.netology.Nmedia.dataBinding.cardpostbinding.PostAdapter
import ru.netology.Nmedia.databinding.ActivityMainBinding
import ru.netology.Nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(object : OnInteractionListener {
            override fun edit(post: Post) {
                viewModel.edit(post)
            }

            override fun like(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun remove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun send(post: Post) {
                viewModel.sendMessage(post.id)
            }

        })

        binding.lists.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.buttomSend.setOnClickListener {
            with(binding.content) {
                val text = text?.toString()
                if (text.isNullOrBlank()) {
                    Toast.makeText(context, "Empty content is not allowed", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                viewModel.changeContent(text)
                viewModel.save()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }

        binding.buttomCansel.setOnClickListener {
            with(binding.content) {
                val text = text?.toString()
                if (text.isNullOrBlank()) {
                    Toast.makeText(context, "Empty content is not allowed", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                viewModel.cansel()
                viewModel.save()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }

        viewModel.edited.observe(this) {
            if (it.id != 0L) {
                with(binding.buttomCansel) {
                    visibility = View.VISIBLE
                }
            } else {
                with(binding.buttomCansel) {
                    visibility = View.GONE
                }
            }

        }


        viewModel.edited.observe(this)
        {

            if (it.id == 0L) {
                return@observe
            }
            with(binding.content) {
                setText(it.content)
                requestFocus()
            }
        }
    }
}



