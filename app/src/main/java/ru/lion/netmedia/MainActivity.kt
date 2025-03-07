package ru.lion.netmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.lion.netmedia.adapter.OnInteractionListener
import ru.lion.netmedia.adapter.PostAdapter
import ru.lion.netmedia.databinding.ActivityMainBinding
import ru.lion.netmedia.dto.Post
import ru.lion.netmedia.utils.AndroidUtils

import ru.lion.netmedia.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {

    val viewModel by viewModels<PostViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Указываем LayoutManager для RecyclerView
        binding.list.layoutManager = LinearLayoutManager(this)

        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.like(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.remove(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
                binding.group?.visibility = View.VISIBLE
                //TODO завершить тут тонкости

            }

            override fun onShare(post: Post) {
                viewModel.share(post.id)
            }

        }
        )
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            val newPosts = adapter.currentList.size < posts.size
            adapter.submitList(posts) {
                if (newPosts) binding.list.smoothScrollToPosition(0)
            }
        }
        viewModel.edited.observe(this) { post ->
            if (post.id != 0L) {
                binding.content?.requestFocus()
                // binding.content?.setText(post.content)
                binding.editContent?.setText(post.content)
                binding.addPost?.visibility = View.GONE
            }
        }
        binding.save?.setOnClickListener {
            val text = binding.content?.text.toString()
            if (text.isBlank()) {
                Toast.makeText(it.context, R.string.error_empty_content, Toast.LENGTH_SHORT).show()
                return@setOnClickListener //TODO Уведомления о том, что поле не может быть пустым не выводятся - исправить!
            }
            viewModel.changeContent(text)
            viewModel.save()

            binding.content?.setText("")
            binding.content?.clearFocus()
            AndroidUtils.hideKeyboard(it)
            binding.addPost?.visibility = View.VISIBLE
            binding.group?.visibility = View.GONE
            //TODO доделать чтобы клавиатура скрывалась при смене фокуса
        }
        binding.cancel?.setOnClickListener {
            binding.group?.visibility = View.GONE
            binding.addPost?.visibility = View.VISIBLE
        }
        binding.addPost?.setOnClickListener {
          binding.groupTwo?.visibility = View.VISIBLE
            binding.addPost.visibility = View.GONE
        }


    }

//    override fun onStart() {
//        super.onStart()
//        // viewModel.view() TODO поправить
//    }
}