package ru.netology.nmedia.viewmodel

import android.view.View
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

private val empty = Post(
    id = 0,
    author = "",
    content = "",
    published = "",
    likesCount = 6099999,
    shareCount = 1199999,
    viewsCount = 1099000,
    likedByMe = false,
    sharedByMe = true
)

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun changeContentAndSave(content: String) {
        edited.value?.let {
            if (content != it.content) {
                repository.save(it.copy(content = content))
            }
            edited.value = empty
        }
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun cancel() {
        edited.value = empty
    }

    fun groupHide(group: Group) {
        group.visibility = View.GONE
    }

    fun likeById(id: Long) = repository.likeById(id)

    fun shareById(id: Long) = repository.shareById(id)

    fun removeById(id: Long) = repository.removeById(id)

}
