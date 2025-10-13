package es.unizar.webeng.lab2.time.controller

import es.unizar.webeng.lab2.time.TimeProvider
import es.unizar.webeng.lab2.time.controller.TimeController
import es.unizar.webeng.lab2.time.toDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TimeControllerUnitTests {
    private lateinit var controller: TimeController
    private lateinit var timeProvider: TimeProvider

    // FakeTimeProvider always returns the same time (deterministic)
    class FakeTimeProvider : TimeProvider {
        override fun now(): LocalDateTime = LocalDateTime.of(2025, 1, 1, 12, 0)
    }

    // Create a new fake provider and inject it into the controller
    // so each test starts with a clean, controlled environment
    @BeforeEach
    fun setup() {
        timeProvider = FakeTimeProvider()
        controller = TimeController(timeProvider)
    }

    @Test
    fun `should return actual time json`() {
        // Prepare the expected result (DTO built from the fixed fake time)
        val expected = timeProvider.now().toDTO()

        // Invoke the time method directly in the controller
        val result = controller.time()

        // Verify that the controller returns the expected DTO
        assertThat(result).isEqualTo(expected)
    }
}
