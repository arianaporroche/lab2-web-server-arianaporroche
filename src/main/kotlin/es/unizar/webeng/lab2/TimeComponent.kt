package es.unizar.webeng.lab2.time

import org.springframework.context.annotation.Profile
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
@Profile("default")
class DefaultTimeService : TimeProvider {
    override fun now() = LocalDateTime.now()
}

@Service
@Profile("prod")
class ProdTimeService : TimeProvider {
    override fun now() = LocalDateTime.now()
}

@Service
@Profile("dev")
class DevTimeService : TimeProvider {
    override fun now() = LocalDateTime.of(2000, 1, 1, 12, 0)
}

// Extension function to convert LocalDateTime to TimeDTO
fun LocalDateTime.toDTO(): TimeDTO = TimeDTO(time = this)
