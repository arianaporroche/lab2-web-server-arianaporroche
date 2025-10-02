package es.unizar.webeng.lab2.time.controller

import es.unizar.webeng.lab2.time.TimeDTO
import es.unizar.webeng.lab2.time.TimeProvider
import es.unizar.webeng.lab2.time.toDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TimeController(
    private val service: TimeProvider,
) {
    @GetMapping("/time")
    fun time(): TimeDTO = service.now().toDTO()
}
