package repository.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_roles")
data class UserRole(@field:[Id Column(name = "user_role_id")] var userRoleId: Int = -1,
               @field:[Column(name = "username")] var user: String = "",
               @field:[Column] var role: String = "")
