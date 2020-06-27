package config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import services.authentication.TrUserDetailsService

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter()
{
    override fun userDetailsService(): UserDetailsService
    {
        return TrUserDetailsService()
    }

    override fun configure(auth: AuthenticationManagerBuilder)
    {
        auth.userDetailsService(userDetailsService())
    }
}