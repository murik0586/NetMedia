package ru.lion.netmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val published:String,
    val content: String,
    val likedByMe: Boolean,
    val likes: Int,
    val shared: Int,
    val views: Int
)