package com.example.blog

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ArticleResource {

    @GetMapping("article")
    fun getArticles() = listOf(
        Article("First Article", "My 1st article content"),
        Article("Second Article", "My 2nd article content"))
}