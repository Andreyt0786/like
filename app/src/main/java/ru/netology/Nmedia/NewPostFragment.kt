package ru.netology.Nmedia

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import ru.netology.Nmedia.databinding.ActivityIntentHandlerBinding.inflate
import ru.netology.Nmedia.databinding.CardpostBinding.inflate
import ru.netology.Nmedia.databinding.FragmentFeedBinding
import ru.netology.Nmedia.databinding.FragmentFeedBinding.inflate
import ru.netology.Nmedia.databinding.FragmentNewPostBinding
import ru.netology.Nmedia.databinding.FragmentNewPostBinding.*

class NewPostFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewPostBinding.inflate(inflater, container, false)

        val text = activity?.intent?.getStringExtra(Intent.EXTRA_TEXT)
        binding.content.setText(text)
        binding.OK.setOnClickListener {
            val content = binding.content.text.toString()
            if (content.isEmpty()) {
                activity?.setResult(RESULT_CANCELED)
            } else {
                activity?.setResult(RESULT_OK, Intent().putExtra(Intent.EXTRA_TEXT, content))
            }
            activity?.finish()
        }
        return binding.root
    }
}
/* binding.buttomCansel.setOnClickListener {
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

}*/

object Contract : ActivityResultContract<String?, String?>() {
    override fun createIntent(context: Context, input: String?) =
        Intent(context, NewPostFragment::class.java).putExtra(Intent.EXTRA_TEXT, input)

    override fun parseResult(resultCode: Int, intent: Intent?) =
        if (resultCode == RESULT_OK) {
            intent?.getStringExtra(Intent.EXTRA_TEXT)
        } else {
            null
        }
}




