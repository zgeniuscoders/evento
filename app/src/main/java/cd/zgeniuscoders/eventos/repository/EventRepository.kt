package cd.zgeniuscoders.eventos.repository

import cd.zgeniuscoders.eventos.models.Event
import com.google.firebase.firestore.CollectionReference

class EventRepository : Repository() {
    override val collection = "events"

    fun allEvents(callback: (List<Event>) -> Unit) {
        this.all().addSnapshotListener { querySnap, error ->
            if (error != null){
                callback(emptyList())
            }

            if (querySnap != null){
                val events = mutableListOf<Event>()
                for(doc in querySnap.documents){
                    val event = doc.toObject(Event::class.java)
                    events.add(event!!)
                }
                callback(events)
            }
        }
    }
}