package repository.authentication

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import repository.entity.User

@Repository
interface UserRepository : CrudRepository<User, String>
{
    @Query("SELECT u FROM User u")
    fun findByName(user: String) : User
}