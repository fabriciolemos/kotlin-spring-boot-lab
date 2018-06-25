package com.example.blog

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Article(val title: String,
                   val content: String,
                   val createdAt: LocalDateTime = LocalDateTime.now(),
                   @Id @GeneratedValue val id: Long? = null)