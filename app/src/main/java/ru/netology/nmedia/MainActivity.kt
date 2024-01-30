package ru.netology.nmedia

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding.inflate
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего.",
            published = "21 мая в 18:36",
            text = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся снами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netology/fyb",
            likesCount = 6099999,
            shareCount = 1099999,
            viewsCount = 1099000,
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            text.text = post.text
            calculate(post.likesCount, likesCount)
            calculate(post.shareCount, shareCount)
            calculate(post.viewsCount, viewsCount)

            if (post.likedByMe) {
                likes.setImageResource(R.drawable.baseline_favorite_24)
            }

            likes.setOnClickListener {
                if (post.likedByMe) post.likesCount-- else post.likesCount++
                post.likedByMe = !post.likedByMe
                likes.setImageResource(if (post.likedByMe) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
                calculate(post.likesCount, likesCount)
            }

            share.setOnClickListener {
                if (post.sharedByMe) post.shareCount++
                calculate(post.shareCount, shareCount)

            }
        }

    }
}