package es.unizar.webeng.lab2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.*

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ErrorTest {
    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun `should return error page with title`() {
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.TEXT_HTML)
        val entity = HttpEntity<String>(headers)

        val response = restTemplate.exchange(
            "http://localhost:$port/",
            HttpMethod.GET,
            entity,
            String::class.java
        )

        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
        assertThat(response.body).contains("<title>Error page</title>")
    }
}