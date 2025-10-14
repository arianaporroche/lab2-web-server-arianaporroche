package es.unizar.webeng.lab2

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class SwaggerIntegrationTest(
    @Autowired val mockMvc: MockMvc,
) {
    @Test
    fun `Swagger UI and API docs are available`() {
        // Verify that /v3/api-docs returns JSON and contains the /time endpoint
        mockMvc
            .get("/v3/api-docs")
            .andExpect {
                status { isOk() }
                content { contentType("application/json") }
                jsonPath("$.paths['/time']") { exists() }
            }

        // Verify that /swagger-ui.html is accessible and redirects to /swagger-ui/index.html
        mockMvc
            .get("/swagger-ui.html")
            .andExpect {
                status { is3xxRedirection() }
            }
    }
}
