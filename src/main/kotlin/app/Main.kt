package app

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.core.env.Environment

@SpringBootApplication
@ComponentScan(basePackages = ["config", "controllers", "repositories", "services"])
class Main
fun main(args: Array<String>)
{
    val ctx: ConfigurableApplicationContext = runApplication<Main>(*args)
    println("Hello")
}

const val eval = "${'$'}{"
const val evalEnd = "}"