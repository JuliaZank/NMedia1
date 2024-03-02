package ru.netology.nmedia.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding.inflate
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(onLike = {
            viewModel.likeById(it.id.toLong())
        }, onShare = {
            viewModel.shareById(it.id.toLong())
        })

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        binding.root.adapter = adapter

    }
}
