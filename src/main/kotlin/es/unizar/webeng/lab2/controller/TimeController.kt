package es.unizar.webeng.lab2.time.controller

import es.unizar.webeng.lab2.time.TimeDTO
import es.unizar.webeng.lab2.time.TimeProvider
import es.unizar.webeng.lab2.time.toDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Time", description = "Operations related to server time")
@RestController
class TimeController(
    private val service: TimeProvider,
) {
    @Operation(
        summary = "Get current server time",
        description = "Returns the current server time in JSON format.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Successful response with current time",
                content = [
                    Content(
                        schema = Schema(implementation = TimeDTO::class),
                        examples = [
                            ExampleObject(
                                value = """{"time": "2025-10-14T12:34:56"}""",
                            ),
                        ],
                    ),
                ],
            ),
        ],
    )
    @GetMapping("/time", produces = ["application/json"])
    fun time(): TimeDTO = service.now().toDTO()
}
