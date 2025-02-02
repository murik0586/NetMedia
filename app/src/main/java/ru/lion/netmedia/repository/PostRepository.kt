package ru.lion.netmedia.repository

import androidx.lifecycle.LiveData
import ru.lion.netmedia.dto.Post

//репозиторий отвечает за бизнес логику, т.е. в данном случае речь про изменения количества лайков и т.п.
//Это в свою очередь  интерфейс
interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()
    fun view()
}