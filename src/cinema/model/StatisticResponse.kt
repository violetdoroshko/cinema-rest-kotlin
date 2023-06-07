package cinema.model

import com.fasterxml.jackson.annotation.JsonProperty

data class StatisticResponse(
    @JsonProperty("current_income")
    var currentIncome: Int,
    @JsonProperty("number_of_available_seats")
    var numberOfAvailableSeats: Int,
    @JsonProperty("number_of_purchased_tickets")
    var numberOfPurchasedTickets: Int
)