package ru.lion.netmedia.viewModel

import androidx.lifecycle.ViewModel
import ru.lion.netmedia.repository.PostRepository
import ru.lion.netmedia.repository.PostRepositoryMemoryImplementation

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryMemoryImplementation()

    val data = repository.get()

    fun like() = repository.like()
}