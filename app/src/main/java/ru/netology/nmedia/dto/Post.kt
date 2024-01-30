package ru.netology.nmedia.dto

data class Post(
    val id: Int,
    val author: String,
    val published: String,
    val text: String,
    var likesCount: Int,
    var shareCount: Int,
    var viewsCount: Int,
    var likedByMe: Boolean = false,
    var sharedByMe: Boolean = true
)

