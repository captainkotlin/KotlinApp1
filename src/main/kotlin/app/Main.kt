package app

import constant.db.Packages
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = [ Packages.config, Packages.controller, Packages.repository, Packages.service ])
class Main
fun main(args: Array<String>)
{
    val ctx: ConfigurableApplicationContext = runApplication<Main>(*args)
    println("Hello")
}

const val eval = "${'$'}{"
const val evalEnd = "}"