package ru.netology.Nmedia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.RequestBuilder.post
import ru.netology.Nmedia.dataBinding.cardpostbinding.OnInteractionListener
import ru.netology.Nmedia.dataBinding.cardpostbinding.postViewHolder
import ru.netology.Nmedia.databinding.FragmentNewPostBinding
import ru.netology.Nmedia.databinding.FragmentPostBinding
import ru.netology.Nmedia.viewmodel.PostViewModel

class PostFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPostBinding.inflate(inflater, container, false)
        val viewModel by viewModels<PostViewModel>(ownerProducer = ::requireParentFragment)
        val viewHolder = postViewHolder(binding.post, object : OnInteractionListener {
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

        viewModel.data.observe(viewLifecycleOwner) { posts ->
          val post = posts.find { it.id == Bundle.post} ?: run {
                findNavController().navigateUp()
                return@observe
            }
        }
        viewHolder.bind(Post(
            id=1L,
            author = "Университет интернет-профессий будущего. Нетология. ",
            content = "# Поста 2 Привет, это новая Нетология!",
            published = " 21 мая в 18:36",
            likedByMe = false,
            likeCount = 0,
            video = "https://www.youtube.com/watch?v=WhWc3b3KhnY"
        ))
        return binding.root
    }


}