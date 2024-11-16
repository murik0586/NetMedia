package ru.lion.netmedia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.viewModels
import ru.lion.netmedia.databinding.ActivityMainBinding
import ru.lion.netmedia.utils.updateCounterDisplay
import ru.lion.netmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()

        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.data
                content.text = post.content
                likesCount.text = updateCounterDisplay(post.likesCount)
                viewsCount.text = updateCounterDisplay(post.viewsCount)
                shareCount.text = updateCounterDisplay(post.shareCount)
                if (post.likedByMe) likes.setImageResource(R.drawable.baseline_favorite_24) else likes.setImageResource(
                    R.drawable.baseline_favorite_border_24
                )

            }
            binding.likes.setOnClickListener { viewModel.like() }
            binding.share.setOnClickListener { viewModel.share() }
            binding.share.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        binding.share.setImageResource(R.drawable.baseline_active_share_24)
                        true
                    }

                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        binding.share.setImageResource(R.drawable.baseline_share_24)
                        binding.share.performClick()
                        true

                    }

                    else -> false

                }

            }


        }


    }

}