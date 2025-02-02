package ru.lion.netmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.lion.netmedia.dto.Post

class PostRepositoryMemoryImplementation : PostRepository {
    private var post = Post(
        1,
        "Уроки разработки - менторство! Присоединяйтесь к нам, покорим Андроид!",
        "25 января в 21:46",
        "Привет, разработчики! 🌟 Android-разработка — это не просто код, это целый мир возможностей. Каждый день мы создаём приложения, которые упрощают жизнь миллионов пользователей по всему миру. Хотите стать частью этой динамичной сферы? Тогда пора изучить Jetpack Compose, разобраться в MVVM и освоить Kotlin! Начните сегодня — завтра ваши приложения могут изменить мир! https://github.com/netology-code/and2-homeworks/tree/master/03_constraint_layout",
        true,
        10,
        1100,
        10,
    )
    private val data = MutableLiveData(post)
    override fun get(): LiveData<Post> = data

    override fun like() {
        post = post.copy(
            likedByMe = !post.likedByMe,
            likes = post.likes + if (post.likedByMe) -1 else +1
        )
        data.value = post
    }

    override fun share() {
        post = post.copy(shared = post.shared + 10)
        data.value = post

    }

    override fun view() {
        post = post.copy(views = post.views +1)
        data.value = post
    }

}