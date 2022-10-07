package ru.netology.Nmedia


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

            override fun play(post: Post) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video.toString()))
                    startActivity(intent)
                }


            override fun send(post: Post) {
                viewModel.sendMessage(post.id)
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }
                // val shareIntent = Intent.createChooser(intent,getString(R.string.))
                // не получается обратиться к string
                startActivity(intent)
            }


        })

        binding.lists.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        val activityLauncher = registerForActivityResult(NewPostActivity.Contract) { text ->
            text ?: return@registerForActivityResult
            viewModel.changeContentAndSave(text)
        }

        binding.add.setOnClickListener {
            activityLauncher.launch(null)
        }

        /*  binding.buttomCansel.setOnClickListener {
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
            if (it.video.isNullOrBlank()) {
                with(binding) {
                    visibility = View.VISIBLE
                }
            } else {
                with(binding.buttomCansel) {
                    visibility = View.GONE
                }
            }

        }
        */

        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }
            activityLauncher.launch(post.content)
        }
    }
}




