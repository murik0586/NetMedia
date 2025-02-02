package ru.lion.netmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import ru.lion.netmedia.databinding.ActivityMainBinding
import ru.lion.netmedia.utils.shortenNumber
import ru.lion.netmedia.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<PostViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.data.observe(this) { post ->
            Log.d("Activity", "Received post: $post")
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likes.text = shortenNumber(post.likes)
                shared.text = shortenNumber(post.shared)
                views.text = shortenNumber(post.views)
                like.setImageResource(if (post.likedByMe) R.drawable.p_thumb_up_24 else R.drawable.outline_thumb_up_24)
                likes.text = shortenNumber(post.likes)

            }

        }

        binding.like.setOnClickListener {
            viewModel.like()//TODO доделать
        }
        binding.shareImage.setOnClickListener {
            viewModel.share()
        }

    }

    override fun onStart() {
        super.onStart()
        viewModel.view()
    }
}