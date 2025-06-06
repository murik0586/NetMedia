package ru.lion.netmedia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.MotionEvent
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.lion.netmedia.R
import ru.lion.netmedia.adapter.OnInteractionListener
import ru.lion.netmedia.adapter.PostAdapter
import ru.lion.netmedia.databinding.ActivityMainBinding
import ru.lion.netmedia.dto.Post
import ru.lion.netmedia.utils.KeyboardUtils

import ru.lion.netmedia.viewModel.PostViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val editPostLauncher = registerForActivityResult(EditPostResultContract()) { result ->
        result ?: return@registerForActivityResult
        viewModel.changeContent(result)
        viewModel.save()
    }

    val viewModel by viewModels<PostViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.list.layoutManager = LinearLayoutManager(this)



        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.like(post.id)
            }

            override fun onRemove(post: Post) {
                Log.i("DELETE", "Совершаем удаление")
                viewModel.remove(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
                intent.putExtra(Intent.EXTRA_TEXT,post.content)
                editPostLauncher.launch(post.content)


//                binding.group.visibility = VISIBLE
//                binding.fab.visibility = GONE
//                binding.content.let { KeyboardUtils.showKeyboard(this@MainActivity, it) }

            }

            override fun onShare(post: Post) {
                val intent = Intent().apply { //TODO разобрать написанное
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                    viewModel.share(post.id)
                }

                val shareIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }
        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            val newPosts = adapter.currentList.size < posts.size
            adapter.submitList(posts) {
                if (newPosts) binding.list.smoothScrollToPosition(0)
            }
        }
        viewModel.edited.observe(this) { post ->
            if (post.id != 0L) {
                binding.content.requestFocus()
                binding.editContent.text = post.content
                binding.editContent.movementMethod =
                    ScrollingMovementMethod()  // Это сделает TextView прокручиваемым
                binding.content.let { KeyboardUtils.showKeyboard(this, it) }
            }
        }

        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }
        binding.fab.setOnClickListener{
            newPostLauncher.launch()
        }

    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (currentFocus != null) {
            KeyboardUtils.hideKeyboard(this)
            currentFocus?.clearFocus()
        }
        return super.dispatchTouchEvent(ev)
    }
}