package repository.project

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import repository.entity.Ticket

@Repository
interface TicketRepository : CrudRepository<Ticket, Long>
{

}
