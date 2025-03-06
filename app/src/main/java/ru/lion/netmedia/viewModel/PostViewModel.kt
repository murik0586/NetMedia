package ru.lion.netmedia.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.lion.netmedia.dto.Post
import ru.lion.netmedia.repository.PostRepository
import ru.lion.netmedia.repository.PostRepositoryMemoryImplementation


private val emptyPost = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    published = "",
    likes = 0,
    shared = 0,
    views = 0,
    deletedByMe = false,
)

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryMemoryImplementation()
    val data: LiveData<List<Post>> = repository.getAll()
    val edited = MutableLiveData(emptyPost)

    fun like(id: Long) = repository.like(id)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = emptyPost
    }

    fun changeContent(content: String) {
        edited.value?.let {
            if (it.content != content) {
                edited.value = it.copy(content = content)
            }
        }
    }

    fun share(id: Long) = repository.share(id)


    // fun view(id: Long) = repository.view(id) - пока не используется!

    fun remove(id: Long) = repository.remove(id)
    fun edit(post: Post) {
        edited.value = post
    }

}