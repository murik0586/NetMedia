package ru.lion.netmedia.repository

import androidx.lifecycle.LiveData
import ru.lion.netmedia.dto.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()

    fun share()


}