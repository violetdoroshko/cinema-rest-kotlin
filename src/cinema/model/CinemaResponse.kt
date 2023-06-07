package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty

data class CinemaResponse
    (
    @JsonProperty("total_rows")
    var rows: Int,
    @JsonProperty("total_columns")
    var columns: Int,
    @JsonProperty("available_seats")
    var availableSeats: List<SeatResponse>
)

