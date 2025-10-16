package es.unizar.webeng.lab2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev") // Use the "dev" profile (loads application-dev.yml)
class IntegrationTest {
    // Inject the actual port number where the test server is running
    @LocalServerPort
    private var port: Int = 0

    // Inject a TestRestTemplate to perform HTTP requests against the running server
    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun `should return home error page with title Error page`() {
        // Create HTTP headers indicating the client accepts HTML responses
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.TEXT_HTML)

        // Wrap headers into an HttpEntity object (no body in this request)
        val entity = HttpEntity<String>(headers)

        // Send a GET request to the root endpoint of the server
        val response =
            restTemplate.exchange(
                "http://localhost:$port/",
                HttpMethod.GET,
                entity,
                String::class.java,
            )

        // Assert that the response has HTTP 404 (Not Found)
        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)

        // Assert that the response body contains the expected title "Error page"
        assertThat(response.body).contains("<title>Error page</title>")
    }
}
