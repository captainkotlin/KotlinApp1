package config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter()
{
    @Autowired
    private lateinit var userDetailsService: service.authentication.UserDetailsService

    override fun userDetailsService(): org.springframework.security.core.userdetails.UserDetailsService
    {
        return userDetailsService;
    }

    override fun configure(http: HttpSecurity)
    {
        http.authorizeRequests()
            .antMatchers("/**")
            .permitAll()
            .and()
            .formLogin()
            .successForwardUrl("/index")
            .and()
            .logout()
    }

    override fun configure(auth: AuthenticationManagerBuilder)
    {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
    }
}