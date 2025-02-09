package ru.lion.netmedia.diffUtil

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import ru.lion.netmedia.dto.Post

class PostDiffCallback() : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Post, newItem: Post): Any? {
        val diffBundle = Bundle()

        if (oldItem.likedByMe != newItem.likedByMe) {
            diffBundle.putBoolean("likedByMe", newItem.likedByMe)
            diffBundle.putInt("likes", newItem.likes)
        }

        if(oldItem.shared != newItem.shared) {
            diffBundle.putInt("shared", newItem.shared)
        }
        if (oldItem.views != newItem.views) {
            diffBundle.putInt("views", newItem.views)
        }
        return if(diffBundle.size() == 0) null else diffBundle
    }
}