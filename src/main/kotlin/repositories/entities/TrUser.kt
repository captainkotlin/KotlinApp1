package repositories.entities

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "users")
class TrUser(
    @field:[Id Column(name = "username")] var user: String = "",
    @field:[Column(name = "password")] var pass: String = ""
)
