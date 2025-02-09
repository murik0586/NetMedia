package ru.lion.netmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.lion.netmedia.adapter.PostAdapter
import ru.lion.netmedia.databinding.ActivityMainBinding

import ru.lion.netmedia.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Указываем LayoutManager для RecyclerView
        binding.list.layoutManager = LinearLayoutManager(this)
        val viewModel by viewModels<PostViewModel>()
        val adapter = PostAdapter {
            viewModel.like(it.id)
        }
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }


    }

//    override fun onStart() {
//        super.onStart()
//        // viewModel.view() TODO поправить
//    }
}