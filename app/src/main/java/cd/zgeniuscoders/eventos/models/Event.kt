package cd.zgeniuscoders.eventos.models

import java.time.LocalDate

data class Event(
    val id: String,
    val userId: String,
    val name: String,
    val description: String,
    val category: String,
    val photo: String,
    val startAt: LocalDate,
    val endAt: LocalDate
){
    constructor(): this("","","","","","",LocalDate.now(),LocalDate.now())
}
