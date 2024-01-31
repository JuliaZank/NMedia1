package ru.netology.nmedia.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.ActivityMainBinding.inflate
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->

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
        binding.likes.setOnClickListener {
            viewModel.like()
        }
    }
}