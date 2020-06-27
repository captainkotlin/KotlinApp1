package services.authentication

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import repositories.TrUserRepository
import repositories.TrUserRoleRepository

@Service
class TrUserDetailsService : UserDetailsService
{
    @Autowired
    private lateinit var userRepository: TrUserRepository

    @Autowired
    private lateinit var userRoleRepository: TrUserRoleRepository

    override fun loadUserByUsername(username: String): TrUserDetails
    {
        val user = userRepository.findByName(username);
        val userRoles = userRoleRepository.findByName(username)
        val authories = userRoles.map { SimpleGrantedAuthority(it.role) }
        return TrUserDetails(user.user, user.pass, authories)
    }
}