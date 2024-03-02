package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {

    private var posts = listOf(
        Post(
            id = 2,
            author = "Нетология. Университет интернет-профессий будущего",
            text = "Знаний хватит на всех: на следующей неделе разбираемся с разработкой мобильных приложений, учимся рассказывать истории и составлять PR-стратегию прямо на бесплатных занятиях \uD83D\uDC47",
            published = "18 сентября в 10:12",
            likesCount = 6099999,
            shareCount = 1199999,
            viewsCount = 1099000,
            likedByMe = false,
            sharedByMe = true
        ),
        Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            text = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likesCount = 6099999,
            shareCount = 1199999,
            viewsCount = 1099000,
            likedByMe = false,
            sharedByMe = true
        ),
    )
    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id.toLong() != id) it else it.copy(
                likedByMe = !it.likedByMe,
                likesCount = if (it.likedByMe) it.likesCount - 1 else it.likesCount + 1
            )
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id.toLong() != id) it else it.copy(
                sharedByMe = it.sharedByMe,
                shareCount = if (!it.sharedByMe) it.shareCount - 1 else it.shareCount + 1
            )
        }
        data.value = posts
    }
}