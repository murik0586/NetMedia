package ru.lion.netmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.lion.netmedia.dto.Post

class PostRepositoryInMemoryImplementation : PostRepository {
    private var post = Post(
        author = "Уроки Андроид разработки",
        data = "29 октября в 00:00",
        content = "Сегодня мы разбираем верстку Андроид,начнем с основ, и продвинемся глубже для начала мы пробуем создать простой вид приложения.Аккурат следуя принципу от простого к сложному! \n https://github.com/netology-code/and2-homeworks/tree/master/03_constraint_layout",
        likesCount = 1,
        viewsCount = 1,
        shareCount = 1,
        likedByMe = false
    )
    val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data


    override fun like() {
        post = post.copy(
            likedByMe = !post.likedByMe,
            likesCount = if (!post.likedByMe) post.likesCount + 1 else post.likesCount - 1
        )
        data.value = post
    }

    override fun share() {
        post = post.copy(shareCount = post.shareCount + 1)
        data.value = post
    }
}