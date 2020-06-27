package repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import repositories.entities.TrUserRole

@Repository
interface TrUserRoleRepository : CrudRepository<TrUserRole, Long>
{
    @Query("SELECT u FROM TrUserRole u WHERE u.user = :user ORDER BY u.userRoleId")
    fun findByName(user: String) : List<TrUserRole>
}