package cd.zgeniuscoders.eventos.models

data class User(
    val id: String,
    val username: String,
    val email: String,
    val photo: String,
    val interests: List<String>?
){
    constructor() : this("","","","", emptyList())
}