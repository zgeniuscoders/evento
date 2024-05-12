package cd.zgeniuscoders.eventos.models

import com.applandeo.materialcalendarview.EventDay
import java.time.LocalDate

data class Event(
    val id: String,
    val userId: String,
    val name: String,
    val description: String,
    val category: String,
    val photo: String,
    val startAt: EventDay?= null,
    val endAt: EventDay? = null
){
    constructor(): this("","","","","","",null,null)
}
