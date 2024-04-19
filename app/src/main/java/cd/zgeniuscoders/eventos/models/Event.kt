package cd.zgeniuscoders.eventos.models

data class Event(
    val id: String,
    val userId: String,
    val name: String,
    val description: String,
    val photo: String,
    val startAt: String,
    val endAt: String
)
