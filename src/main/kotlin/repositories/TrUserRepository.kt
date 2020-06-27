package repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import repositories.entities.TrUser

@Repository
interface TrUserRepository : CrudRepository<TrUser, String>
{
    @Query("SELECT u FROM TrUser u")
    fun findByName(user: String) : TrUser
}