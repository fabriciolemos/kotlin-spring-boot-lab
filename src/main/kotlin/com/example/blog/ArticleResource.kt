package com.example.blog

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ArticleResource (@Autowired val articleRepository: ArticleRepository) {

    @GetMapping("article")
    fun getArticles(): Iterable<Article> = articleRepository.findAll()
}