package ru.lion.netmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.lion.netmedia.R
import ru.lion.netmedia.databinding.CardPostBinding
import ru.lion.netmedia.diffUtil.PostDiffCallback
import ru.lion.netmedia.dto.Post
import ru.lion.netmedia.utils.shortenNumber
import ru.lion.netmedia.viewModel.PostViewModel

typealias OnLikeListener = (post: Post) -> Unit

class PostAdapter(private val onLikeListener: OnLikeListener) : ListAdapter<Post, PostViewHolder> (PostDiffCallback()) {
//    var list = emptyList<Post>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
       val post = getItem(position)
        holder.bind(post)
    }

//    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
//        val post = list[position]
//        holder.bind(post)
//    }

//    override fun getItemCount(): Int = list.size
}

class PostViewHolder(private val binding: CardPostBinding,
                     private val onLikeListener: OnLikeListener): RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likes.text = shortenNumber(post.likes)
            shared.text = shortenNumber(post.shared)
            views.text = shortenNumber(post.views)
            like.setImageResource(if (post.likedByMe) R.drawable.p_thumb_up_24 else R.drawable.outline_thumb_up_24)
            likes.text = shortenNumber(post.likes)
            like.setOnClickListener {
                onLikeListener(post)
            }
        }
    }
}