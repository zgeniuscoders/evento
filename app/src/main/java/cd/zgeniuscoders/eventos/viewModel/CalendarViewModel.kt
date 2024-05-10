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
    private var _month = MutableLiveData<Int>()
    private var _year = MutableLiveData<Int>()
    private var _dayOfMonth = MutableLiveData<Int>()

    private var _updateRecycler = MutableLiveData<List<Event>>()

    var events: LiveData<List<Event>> = _events
    var updateRecycler: LiveData<List<Event>> = _updateRecycler

    var month: LiveData<Int> = _month
    var year: LiveData<Int> = _year
    var dayOfMonth: LiveData<Int> = _dayOfMonth

    fun allEvents(event: List<Event>) {
//        repository.allEvents { events ->
//            _events.postValue(events)
//        }
        _events.postValue(event)
        _month.postValue(calendar.get(Calendar.MONTH) + 1)
    }


    fun showDatePicker(context: Context, events: List<Event>) {

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dateOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, dayOfMonth ->
                updateCalendarData(selectedYear, selectedMonth+1, events)
                _month.postValue(selectedMonth+1)
                _year.postValue(selectedYear)
                _dayOfMonth.postValue(dayOfMonth)
            },
            year,
            month,
            dateOfMonth
        )
        datePicker.show()


    }

    fun updateCalendarData(year: Int, month: Int, eventsList: List<Event>) {
        var newMonth = if (month < 10) {
            "0$month"
        } else {
            month
        }
        val filteredEvents =
            eventsList.filter { event -> event.startAt.monthValue == newMonth && event.startAt.year == year }
        _events.postValue(filteredEvents)

    }

    fun filterEventsForMonthYear(selectedDate: LocalDate, events: List<Event>) {
        val filteredEvents =
            events.filter { event -> event.startAt.year == selectedDate.year && event.startAt.month == selectedDate.month }
        _updateRecycler.postValue(filteredEvents)
    }

}