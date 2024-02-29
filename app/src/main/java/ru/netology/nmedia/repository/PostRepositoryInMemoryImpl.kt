package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl: PostRepository {

    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего.",
        published = "21 мая в 18:36",
        text = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся снами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netology/fyb",
        likesCount = 6099999,
        shareCount = 1199999,
        viewsCount = 1099000
    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {

        post = post.copy(likedByMe = !post.likedByMe)

        if (!post.likedByMe) post.likesCount-- else post.likesCount++

        data.value = post
    }

    override fun share() {
        post = post.copy(sharedByMe  = post.sharedByMe)
        if (post.sharedByMe) post.shareCount++

        data.value = post
    }
}
