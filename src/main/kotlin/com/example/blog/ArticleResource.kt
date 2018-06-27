package com.example.blog

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ArticleResource(val articleRepository: ArticleRepository) {

    @GetMapping("article")
    fun getArticles(): Iterable<Article> = articleRepository.findAll()
}