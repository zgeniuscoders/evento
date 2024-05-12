package cd.zgeniuscoders.eventos.viewModel

import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cd.zgeniuscoders.eventos.models.Event
import cd.zgeniuscoders.eventos.repository.EventRepository
import java.time.LocalDate
import java.util.Calendar

class CalendarViewModel: ViewModel() {

    private var repository = EventRepository()
    private val calendar = Calendar.getInstance()

    private var _events = MutableLiveData<List<Event>>()

    var events: LiveData<List<Event>> = _events

}