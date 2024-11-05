package ru.lion.netmedia.dto


data class Post(
    val author: String,
    val data: String,
    var content: String,
    var likesCount: Int,
    var shareCount: Int,
    var viewsCount: Int,
    var likedByMe: Boolean = false
)
