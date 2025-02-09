package ru.lion.netmedia.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.lion.netmedia.R
import ru.lion.netmedia.databinding.CardPostBinding
import ru.lion.netmedia.diffUtil.PostDiffCallback
import ru.lion.netmedia.dto.Post
import ru.lion.netmedia.utils.shortenNumber


typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit

class PostAdapter(
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener
) :
    ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {
//    var list = emptyList<Post>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int,payloads:List<Any>) {
        if(payloads.isNotEmpty()) {
            payloads.forEach{payload ->
                (payload as? Bundle)?.let{holder.updatePartial(it)}
            }
        }else onBindViewHolder(holder,position)
    }

//    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
//        val post = list[position]
//        holder.bind(post)
//    }

//    override fun getItemCount(): Int = list.size
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener
) : RecyclerView.ViewHolder(binding.root) {
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
            shareImage.setOnClickListener {
                onShareListener(post)
            }
        }
    }
    fun updatePartial(diffBundle: Bundle) {
        diffBundle.keySet().forEach { key ->
            when (key) {
                "likes" -> {
                    val likes = diffBundle.getInt("likes")
                    binding.likes.text = shortenNumber(likes) // Сначала обновляем текст
                }
                "likedByMe" -> {
                    val likedByMe = diffBundle.getBoolean("likedByMe")
                    binding.like.setImageResource(
                        if (likedByMe) R.drawable.p_thumb_up_24 else R.drawable.outline_thumb_up_24
                    ) // Потом обновляем иконку
                }
                "shared" -> {
                    val shared = diffBundle.getInt("shared")
                    binding.shared.text = shortenNumber(shared)
                }
            }
        }
    }
}