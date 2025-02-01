package ru.lion.netmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.lion.netmedia.databinding.ActivityMainBinding
import ru.lion.netmedia.utils.shortenNumber
import ru.lion.netmedia.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likes.text = shortenNumber(post.likes)
                shared.text = shortenNumber(post.shared)
                //views.text = shortenNumber(++post.views)//TODO тоже перенести!

                like.setImageResource(if (post.likedByMe) R.drawable.p_thumb_up_24 else R.drawable.outline_thumb_up_24)
                likes.text = shortenNumber(post.likes)

            }
        }
        binding.like.setOnClickListener {
            viewModel.like()//TODO доделать
        }
        binding.shareImage.setOnClickListener {
            //TODO доделать
        }

    }
}