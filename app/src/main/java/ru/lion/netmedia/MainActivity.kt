package ru.lion.netmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.lion.netmedia.databinding.ActivityMainBinding
import ru.lion.netmedia.dto.Post
import ru.lion.netmedia.utils.updateCounterDisplay

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            author = "Уроки Андроид разработки",
            data = "29 октября в 00:00",
            content = "Сегодня мы разбираем верстку Андроид,начнем с основ, и продвинемся глубже для начала мы пробуем создать простой вид приложения.Аккурат следуя принципу от простого к сложному! \n https://github.com/netology-code/and2-homeworks/tree/master/03_constraint_layout",
            likesCount = 1600000,
            viewsCount = 15000,
            shareCount = 1500,
            likedByMe = false
        )
        with(binding) {
            author.text = post.author
            published.text = post.data
            content.text = post.content
            likesCount.text = updateCounterDisplay(post.likesCount)
            viewsCount.text = updateCounterDisplay(post.viewsCount)
            shareCount.text = updateCounterDisplay(post.shareCount)
            if (post.likedByMe) {
                likes.setImageResource(R.drawable.baseline_favorite_border_24)
            }
            likes.setOnClickListener {
                post.likedByMe = !post.likedByMe
                if (post.likedByMe) {
                    post.likesCount++
                    likes.setImageResource(R.drawable.baseline_favorite_24)

                } else {
                    post.likesCount--
                    likes.setImageResource(R.drawable.baseline_favorite_border_24)
                }
                val textCountLikes = updateCounterDisplay(post.likesCount)
                likesCount.text = textCountLikes

            }
            share.setOnClickListener {
                post.shareCount += 10
                //share.setImageResource(R.drawable.baseline_active_share_24)
                shareCount.text = updateCounterDisplay(post.shareCount)
            }
        }
    }

}