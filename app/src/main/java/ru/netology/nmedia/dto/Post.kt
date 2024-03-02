package ru.netology.nmedia.dto

data class Post(
    val id: Int,
    val author: String,
    val published: String,
    val text: String,
    val likesCount: Int,
    val shareCount: Int,
    val viewsCount: Int,
    val likedByMe: Boolean = false,
    val sharedByMe: Boolean = true
)

