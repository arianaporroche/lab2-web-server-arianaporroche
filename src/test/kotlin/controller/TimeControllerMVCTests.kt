package es.unizar.webeng.lab2.time.controller

import es.unizar.webeng.lab2.time.TimeProvider
import es.unizar.webeng.lab2.time.controller.TimeController
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@WebMvcTest(TimeController::class)
class TimeControllerMVCTests {
    // MockMvc simulates HTTP requests to the controller without starting a real server
    @Autowired
    private lateinit var mockMvc: MockMvc

    // @MockBean replaces the real TimeProvider bean in the Spring context with a Mockito mock
    @MockBean
    private lateinit var timeProvider: TimeProvider

    // Define the behavior of the mocked TimeProvider, it will always return the same fixed time
    @BeforeEach
    fun setup() {
        given(timeProvider.now()).willReturn(LocalDateTime.of(2025, 1, 1, 12, 0))
    }

    @Test
    fun `should return API response as JSON`() {
        // Expected JSON time value (in ISO_LOCAL_DATE_TIME format)
        val expectedTime =
            LocalDateTime
                .of(2025, 1, 1, 12, 0)
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

        // Perform a GET request to the /time endpoint
        // and verify that the response has the expected status, content type, and JSON body
        mockMvc
            .perform(get("/time"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.time", equalTo(expectedTime)))
    }
}
