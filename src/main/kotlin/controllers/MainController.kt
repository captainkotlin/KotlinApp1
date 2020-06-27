package controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController
{
    @Autowired
    private lateinit var env: Environment

    @GetMapping("index")
    fun getIndex(): String
    {
        return "Hello"
    }
}