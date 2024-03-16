package ru.netology.nmedia.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.util.AndroidUtils.focusAndShowKeyboard
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(object : OnInteractionListener {

            override fun onLike(post: Post) {
                viewModel.likeById(post.id.toLong())
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id.toLong())
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id.toLong())
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onGroup(group: Int) {
                viewModel.groupHide(binding.group)
            }
        })

        binding.list.adapter = adapter

        viewModel.data.observe(this) { posts ->
            val newPost = adapter.currentList.size < posts.size && adapter.currentList.size > 0

            adapter.submitList(posts) {
                if (newPost) {
                    binding.list.smoothScrollToPosition(0)
                }
            }
        }

        viewModel.edited.observe(this) { post ->
            if (post.id.toLong() != 0L) {
                binding.edit.setText(post.content)
                binding.edit.focusAndShowKeyboard()
            }
        }

        viewModel.edited.observe(this) { post ->
            if (post.id.toLong() != 0L) {
                binding.postCopy.text = post.content
                binding.group.visibility = View.VISIBLE
            }
        }

        binding.save.setOnClickListener {
            val text = binding.edit.text.toString().trim()
            if (text.isEmpty()) {
                Toast.makeText(this, R.string.error_empty_content, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            viewModel.changeContentAndSave(text)
            binding.edit.setText("")
            binding.edit.clearFocus()
            AndroidUtils.hideKeyboard(it)
            viewModel.groupHide(binding.group)
        }

        binding.cancel.setOnClickListener {

            viewModel.groupHide(binding.group)
            viewModel.cancel()

            binding.edit.setText("")
            binding.edit.clearFocus()
            AndroidUtils.hideKeyboard(it)
            return@setOnClickListener
        }
    }
}
