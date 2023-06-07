package cinema.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ReturnResponse (
    @JsonProperty("returned_ticket")
    val seat:SeatResponse
        )