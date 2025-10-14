package es.unizar.webeng.lab2.time.controller

import es.unizar.webeng.lab2.time.TimeProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class DevProfileIntegrationTest {
    @Autowired
    private lateinit var timeProvider: TimeProvider

    @Test
    fun shouldUseDevTimeProviderBean() {
        val time = timeProvider.now()
        assertThat(time.year).isEqualTo(2000)
    }
}

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("prod")
class ProdProfileIntegrationTest {
    @Autowired
    private lateinit var timeProvider: TimeProvider

    @Test
    fun shouldUseProdTimeProviderBean() {
        val time = timeProvider.now()
        assertThat(time.year).isEqualTo(LocalDateTime.now().year)
    }
}
