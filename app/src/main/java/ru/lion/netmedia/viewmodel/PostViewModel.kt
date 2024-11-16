package ru.lion.netmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.lion.netmedia.repository.PostRepository
import ru.lion.netmedia.repository.PostRepositoryInMemoryImplementation

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImplementation()

    val data = repository.get()

    fun like() = repository.like()

    fun share() = repository.share()
}