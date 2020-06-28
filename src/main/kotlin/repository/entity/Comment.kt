package repository.entity

import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Comment(@field:Id val id: Long = 0,
                   val body: String = "",
                   val author: String = "",
                   val createdDate: LocalDateTime = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC),
                   @field:[ManyToOne JoinColumn] val ticket: Ticket = Ticket.emptyTicket
)
