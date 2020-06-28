package repository.entity

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @field:[Id Column(name = "username")] var user: String = "",
    @field:[Column(name = "password")] var pass: String = ""
)
