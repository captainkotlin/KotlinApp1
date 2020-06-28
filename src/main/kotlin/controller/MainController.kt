package controller

import config.project.RepositoryConfig
import constant.db.project.BeanNames
import constant.web.Pages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import repository.entity.Ticket
import repository.project.TicketRepository
import java.lang.NullPointerException

@Controller
@Scope("session")
class MainController
{
    @Autowired
    private lateinit var ticketRepository: TicketRepository

//    @Autowired
//    private lateinit var repoConfig: LocalContainerEntityManagerFactoryBean

    @GetMapping(Pages.index)
    fun getIndex(): String
    {
        return Pages.index
    }

    @GetMapping(Pages.board)
    fun getBoard() : String
    {
        return Pages.board
    }

    @GetMapping(Pages.board + "/" + "{projectName}")
    fun get(@PathVariable projectName: String?) : String
    {
        return "index"
    }

}