package cd.zgeniuscoders.eventos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cd.zgeniuscoders.eventos.models.Event
import cd.zgeniuscoders.eventos.repository.EventRepository

class EventViewModel : ViewModel() {

    private val eventRepository = EventRepository()
    private val _events = MutableLiveData<List<Event>>()
    private val _event = MutableLiveData<Event>()
    private val _isCreated = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()

    val events: LiveData<List<Event>> = _events
    val event: LiveData<Event> = _event
    val isCreated: LiveData<Boolean> = _isCreated
    val error: LiveData<String> = _error

    fun create(title: String, description: String, startAt: String, endAt: String) {

    }


    fun all() {
        eventRepository.allEvents {
            _events.postValue(it)
        }
    }

    fun find(id: String) {

    }

}