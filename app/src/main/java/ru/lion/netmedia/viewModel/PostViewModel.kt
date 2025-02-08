package ru.lion.netmedia.viewModel

import androidx.lifecycle.ViewModel
import ru.lion.netmedia.repository.PostRepository
import ru.lion.netmedia.repository.PostRepositoryMemoryImplementation

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryMemoryImplementation()

    val data = repository.getAll()

    fun like(id: Long) = repository.like(id)
    fun share(id: Long) = repository.share(id)
    fun view(id: Long) = repository.view(id)
}