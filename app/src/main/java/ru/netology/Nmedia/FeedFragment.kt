package ru.netology.Nmedia


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.Nmedia.dataBinding.cardpostbinding.OnInteractionListener
import ru.netology.Nmedia.dataBinding.cardpostbinding.PostAdapter
import ru.netology.Nmedia.databinding.FragmentFeedBinding
import ru.netology.Nmedia.viewmodel.PostViewModel


class FeedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)


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
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
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

        viewModel.edited.observe(viewLifecycleOwner) { post ->
            if (post.id == 0L) {
                return@observe
            }
            activityLauncher.launch(post.content)
        }
        return binding.root
    }
}




