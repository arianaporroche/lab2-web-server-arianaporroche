package es.unizar.webeng.lab2.time

import org.springframework.stereotype.Service
import java.time.LocalDateTime

// DTO definition
data class TimeDTO(
    val time: LocalDateTime,
)

// TimeProvider interface definition
interface TimeProvider {
    fun now(): LocalDateTime
}

// TimeProvider service implementation
@Service
class TimeService : TimeProvider {
    override fun now(): LocalDateTime = LocalDateTime.now()
}

// Extension function to convert LocalDateTime to TimeDTO
fun LocalDateTime.toDTO(): TimeDTO = TimeDTO(time = this)
