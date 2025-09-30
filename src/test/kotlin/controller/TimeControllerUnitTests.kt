package es.unizar.webeng.lab2.time.controller

import es.unizar.webeng.lab2.time.controller.TimeController
import es.unizar.webeng.lab2.time.TimeProvider
import es.unizar.webeng.lab2.time.toDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.ui.ExtendedModelMap
import java.time.LocalDateTime


class TimeControllerUnitTests {
    private lateinit var controller: TimeController
    private lateinit var timeProvider: TimeProvider

    // Fake provider que devuelve siempre la misma hora
    class FakeTimeProvider : TimeProvider {
        override fun now(): LocalDateTime = LocalDateTime.of(2025, 1, 1, 12, 0)
    }

    
    @BeforeEach
    fun setup() {
        timeProvider = FakeTimeProvider()
        controller = TimeController(timeProvider)
    }

    @Test
    fun `should return actual time json`() {
        val time = controller.time()
        
        assertThat(time).isEqualTo(timeProvider.now().toDTO())
    }
}