package repository.entity

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import javax.persistence.*

@Entity
data class Ticket(@field:[Id GeneratedValue(strategy=GenerationType.SEQUENCE)] var id: Long = 0,
                  var projectId: String = "",
                  var description: String = "",
                  var body: String = "",
                  var createdDate: LocalDateTime = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC),
                  var lastModifiedDate: LocalDateTime = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC),
                  var author: String = "",
                  var assignee: String = "",

                  @field:OneToMany(
                      targetEntity = Comment::class,
                      //mappedBy="college",
                      fetch = FetchType.LAZY
                  )
                  var comments: List<Comment> = emptyList()
)
{
    companion object
    {
        val emptyTicket = Ticket()
    }
}

