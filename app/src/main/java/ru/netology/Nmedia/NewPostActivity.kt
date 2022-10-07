package ru.netology.Nmedia

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.Nmedia.databinding.ActivityMainBinding
import ru.netology.Nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val text = intent?.getStringExtra(Intent.EXTRA_TEXT)
        binding.content.setText(text)
        binding.OK.setOnClickListener {
            val content = binding.content.text.toString()
            if (content.isEmpty()) {
                setResult(RESULT_CANCELED)
            } else {
                setResult(RESULT_OK, Intent().putExtra(Intent.EXTRA_TEXT, content))
            }
            finish()
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
                Intent(context, NewPostActivity::class.java).putExtra(Intent.EXTRA_TEXT, input)

            override fun parseResult(resultCode: Int, intent: Intent?) =
                if (resultCode == RESULT_OK) {
                    intent?.getStringExtra(Intent.EXTRA_TEXT)
                } else {
                    null
                }
        }
    }



