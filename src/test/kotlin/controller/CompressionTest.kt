package es.unizar.webeng.lab2.time.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class CompressionTest(
    @Autowired val restTemplate: TestRestTemplate,
) {
    @LocalServerPort
    var port: Int = 0

    @Test
    fun `should compress response when Accept-Encoding is gzip`() {
        val headers = HttpHeaders().apply { add("Accept-Encoding", "gzip") }
        val entity = HttpEntity<String>(headers)
        val response = restTemplate.exchange("http://localhost:$port/time", HttpMethod.GET, entity, String::class.java)
        assert(response.headers["Content-Encoding"]?.contains("gzip") == true)
    }
}
