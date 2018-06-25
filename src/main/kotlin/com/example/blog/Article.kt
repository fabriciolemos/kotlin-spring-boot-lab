package com.example.blog

import java.time.LocalDateTime

data class Article(val title: String,
                   val content: String,
                   val createdAt: LocalDateTime = LocalDateTime.now(),
                   val id: Long? = null)