package ru.lion.netmedia.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.lion.netmedia.dto.Post
import ru.lion.netmedia.repository.PostRepository
import ru.lion.netmedia.repository.PostRepositoryMemoryImplementation

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryMemoryImplementation()


    val data: LiveData<List<Post>> = repository.getAll()


    fun like(id: Long) = repository.like(id)


    fun share(id: Long) = repository.share(id)


   // fun view(id: Long) = repository.view(id) - пока не используется!

    fun remove(id: Long) = repository.remove(id)

}