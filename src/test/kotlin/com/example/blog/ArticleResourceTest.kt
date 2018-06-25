package com.example.blog

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArticleResourceTest(@Autowired private val restTemplate: TestRestTemplate) {

    @Test
    fun `should return a list of Articles`() {
        val response  = restTemplate.getForEntity<List<Article>>("/article")
        assertEquals(2, response.body?.size)
    }
}
