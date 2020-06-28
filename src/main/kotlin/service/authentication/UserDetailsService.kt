package service.authentication

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import repository.authentication.UserRepository
import repository.authentication.UserRoleRepository

@Service
class UserDetailsService : UserDetailsService
{
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var userRoleRepository: UserRoleRepository

    override fun loadUserByUsername(username: String): UserDetails
    {
        val user = userRepository.findByName(username);
        val userRoles = userRoleRepository.findByName(username)
        val authories = userRoles.map { SimpleGrantedAuthority(it.role) }
        return UserDetails(user.user, user.pass, authories)
    }
}