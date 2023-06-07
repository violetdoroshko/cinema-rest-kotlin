package cinema.service

import cinema.constant.Constants.COLUMNS
import cinema.constant.Constants.FIRST_ROWS_PRICE
import cinema.constant.Constants.FIRST_ROWS_RAGE
import cinema.constant.Constants.LAST_ROWS_PRICE
import cinema.constant.Constants.PASSWORD
import cinema.constant.Constants.ROWS
import cinema.exception.exception.AccessDeniedException
import cinema.exception.exception.ValidationException
import cinema.model.*
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class CinemaService {


    private val availableSeats = mutableListOf<SeatResponse>()
    private val purchasedSeats = ConcurrentHashMap<String, SeatResponse>()

    init {
        for (row in 1..ROWS) {
            for (number in 1..COLUMNS) {
                val price = if (row in FIRST_ROWS_RAGE) FIRST_ROWS_PRICE else LAST_ROWS_PRICE
                availableSeats.add(SeatResponse(row, number, price))
            }
        }
    }

    fun getAvailableSeats(): CinemaResponse {
        return CinemaResponse(ROWS, COLUMNS, availableSeats)
    }

    fun purchase(request: PurchaseRequest): PurchaseResponse {
        this.validate(request)

        val seat = findSeat(request.row, request.column)!!
        val token = UUID.randomUUID().toString()

        availableSeats.remove(seat)
        purchasedSeats[token] = seat

        return PurchaseResponse(token, seat)
    }

    fun refundSeat(token: String): ReturnResponse {
        val seat = purchasedSeats[token] ?: throw ValidationException("Wrong token!")

        purchasedSeats.remove(token)
        availableSeats.add(seat)

        return ReturnResponse(seat)
    }

    private fun availableByRowAndColumn(row: Int, column: Int): Boolean {
        return findSeat(row, column) != null
    }

    private fun findSeat(row: Int, number: Int): SeatResponse? {
        return availableSeats.find { it.row == row && it.column == number }
    }


    private fun validate(request: PurchaseRequest) {
        if ((request.column !in 1..COLUMNS) || (request.row !in 1..ROWS))
            throw ValidationException("The number of a row or a column is out of bounds!")

        if (!this.availableByRowAndColumn(request.row, request.column)) {
            throw ValidationException("The ticket has been already purchased!")
        }
    }

    fun getStatistic(password: String?): StatisticResponse {
        if (password == null || password != PASSWORD)
            throw AccessDeniedException("The password is wrong!")

        return StatisticResponse(
            currentIncome = calculateCurrentIncome(),
            numberOfAvailableSeats = availableSeats.size,
            numberOfPurchasedTickets = purchasedSeats.size
        )
    }

    private fun calculateCurrentIncome(): Int =
        purchasedSeats.values.stream().map { seat -> seat.price }.toList().sum()

}