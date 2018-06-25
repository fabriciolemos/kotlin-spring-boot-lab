package com.example.blog

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldResource {

    @GetMapping("hello")
    fun hello() = "Hello"
}
