package cinema.model

data class PurchaseResponse(
    val token: String,
    val ticket: SeatResponse
)