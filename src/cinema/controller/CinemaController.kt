package cinema.controller;

import cinema.model.*
import cinema.service.CinemaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CinemaController @Autowired constructor(
    private val service: CinemaService
) {

    @GetMapping("/seats")
    fun getSeats(): ResponseEntity<CinemaResponse> =
        ResponseEntity.ok(service.getAvailableSeats())

    @PostMapping("/purchase")
    fun purchaseSeat(@RequestBody request: PurchaseRequest): ResponseEntity<PurchaseResponse> =
        ResponseEntity.ok(service.purchase(request))

    @PostMapping("/return")
    fun refundSeat(@RequestBody request: ReturnRequest): ResponseEntity<ReturnResponse> =
        ResponseEntity.ok(service.refundSeat(request.token))

    @PostMapping("/stats")
    fun getStatistic(@RequestParam(required = false) password: String?): ResponseEntity<StatisticResponse> =
        ResponseEntity.ok(service.getStatistic(password))

}
