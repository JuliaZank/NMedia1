package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.calculate
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

interface OnInteractionListener {
    fun onLike(post: Post) {}
    fun onEdit(post: Post) {}
    fun onShare(post: Post) {}
    fun onRemove(post: Post) {}
    fun onCancel(post: Post) {}
    fun onGroup(group: Int) {}
}

class PostsAdapter(private val OnInteractionListener: OnInteractionListener) :
    ListAdapter<Post, PostViewHolder>(PostDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, OnInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val OnInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {

        with(binding) {
            OnInteractionListener.onGroup(R.id.group)
            author.text = post.author
            published.text = post.published
            content.text = post.content

            likes.setImageResource(if (post.likedByMe) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)

            calculate(post.likesCount, likesCount)
            calculate(post.shareCount, shareCount)
            calculate(post.viewsCount, viewsCount)

            likes.setOnClickListener {
                OnInteractionListener.onLike(post)
            }
            share.setOnClickListener {
                OnInteractionListener.onShare(post)
            }
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                OnInteractionListener.onRemove(post)
                                true
                            }

                            R.id.edit -> {
                                OnInteractionListener.onEdit(post)
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }
        }
    }
}


object PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem
}