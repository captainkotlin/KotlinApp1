package controllers

import constants.WebPages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Controller
class MainController
{
    @Autowired
    private lateinit var env: Environment

    @GetMapping(WebPages.index)
    fun getIndex(): String
    {
        return WebPages.index
    }

    @GetMapping(WebPages.board)
    fun getBoard() : String
    {
        return WebPages.board
    }
}