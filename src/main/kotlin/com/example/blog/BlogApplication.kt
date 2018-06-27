package com.example.blog

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class BlogApplication {

    @Bean
    fun initDatabase(articleRepository: ArticleRepository) = CommandLineRunner {
        val articles = listOf(
            Article("My First Article", "my 1st article content"),
            Article("My Second Article", "my 2nd article content"))
        articleRepository.saveAll(articles)
    }
}

fun main(args: Array<String>) {
    runApplication<BlogApplication>(*args)
}
