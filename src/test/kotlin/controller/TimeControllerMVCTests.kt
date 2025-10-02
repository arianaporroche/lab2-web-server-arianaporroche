package es.unizar.webeng.lab2.time.controller

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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
import es.unizar.webeng.lab2.time.TimeProvider
import es.unizar.webeng.lab2.time.controller.TimeController


@WebMvcTest(TimeController::class)
class TimeControllerMVCTests {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var timeProvider: TimeProvider

    @BeforeEach
    fun setup() {
        // We always return the same time
        given(timeProvider.now()).willReturn(LocalDateTime.of(2025, 1, 1, 12, 0))
    }

    @Test
    fun `should return API response as JSON`() {
        val expectedTime =
            LocalDateTime
                .of(2025, 1, 1, 12, 0)
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

        mockMvc
            .perform(get("/time"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.time", equalTo(expectedTime)))
    }
}
