package ru.lion.netmedia.dto


data class Post(
    val author: String,
    val data: String,
    val content: String,
    val likesCount: Int,
    val shareCount: Int,
    val viewsCount: Int,
    val likedByMe: Boolean = false
)
