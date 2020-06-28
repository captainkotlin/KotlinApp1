package repository.authentication

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import repository.entity.UserRole

@Repository
interface UserRoleRepository : CrudRepository<UserRole, Long>
{
    @Query("SELECT u FROM UserRole u WHERE u.user = :user ORDER BY u.userRoleId")
    fun findByName(user: String) : List<UserRole>
}