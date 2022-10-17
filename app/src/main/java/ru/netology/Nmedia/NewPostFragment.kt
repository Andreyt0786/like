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
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import ru.netology.Nmedia.Utils.StringArg
import ru.netology.Nmedia.databinding.CardpostBinding.inflate
import ru.netology.Nmedia.databinding.FragmentFeedBinding
import ru.netology.Nmedia.databinding.FragmentFeedBinding.inflate
import ru.netology.Nmedia.databinding.FragmentNewPostBinding
import ru.netology.Nmedia.databinding.FragmentNewPostBinding.*
import ru.netology.Nmedia.viewmodel.PostViewModel

class NewPostFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewPostBinding.inflate(inflater, container, false)
        arguments?.textArg?.let {
            binding.content.setText(it)
        }
        // arguments?.textArg?.let(binding.content::setText)
        val viewModel by viewModels<PostViewModel>(ownerProducer = ::requireParentFragment)

        val text = activity?.intent?.getStringExtra(Intent.EXTRA_TEXT)
        //binding.content.setText(text) //Если что закоментировать
        binding.OK.setOnClickListener {
            val text = binding.content.text.toString()
            if (text.isNotBlank()) {
                viewModel.changeContentAndSave(text)
            }
            findNavController().navigateUp()
        }

       // binding.content.setOnClickListener {
      //      findNavController().navigate(R.id.action_feedFragment_to_postFragment)
      //  }
        return binding.root
    }


    companion object {
        var Bundle.textArg by StringArg
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

/*object Contract : ActivityResultContract<String?, String?>() {
    override fun createIntent(context: Context, input: String?) =
        Intent(context, NewPostFragment::class.java).putExtra(Intent.EXTRA_TEXT, input)

    override fun parseResult(resultCode: Int, intent: Intent?) =
        if (resultCode == RESULT_OK) {
            intent?.getStringExtra(Intent.EXTRA_TEXT)
        } else {
            null
        }
}
*/



