package cd.zgeniuscoders.eventos.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import cd.zgeniuscoders.eventos.R
import cd.zgeniuscoders.eventos.databinding.FragmentCalendarBinding
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.builders.DatePickerBuilder
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import java.util.Calendar


class CalendarFragment : Fragment(), OnDayClickListener, OnSelectDateListener {
    private lateinit var binding: FragmentCalendarBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(layoutInflater)

        binding.calendarView.setOnDayClickListener(this)
//        binding.fabButton.setOnClickListener { openDatePicker() }

        val calendar = Calendar.getInstance()

        val notes: MutableList<EventDay> = mutableListOf<EventDay>()
        val note: EventDay = EventDay(calendar, R.color.primary)
        note.calendar.set(2024,4,12)

        val note2: EventDay = EventDay(calendar, R.color.primary)
        note.calendar.set(2024,5,15)

        notes.add(note)
        notes.add(note2)
        binding.calendarView.setEvents(notes)

        return binding.root
    }

    override fun onDayClick(eventDay: EventDay) {
        val clickedDayCalendar: Calendar = eventDay.calendar
    }

    override fun onSelect(calendar: List<Calendar>) {
        val clickedDayCalendar = calendar
    }

//    private fun openDatePicker() {
//        DatePickerBuilder(requireContext(), this)
//            .pickerType(CalendarView.ONE_DAY_PICKER)
//            .headerColor(R.color.primary)
//            .todayLabelColor(R.color.primary)
//            .selectionColor(R.color.gray)
//            .dialogButtonsColor(R.color.primary)
//            .build()
//            .show()
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val calendarDays: List<CalendarDay> = ArrayList()

        val calendar = Calendar.getInstance()
        val calendarDay = CalendarDay(calendar)

//        calendarDay.setImageResource(R.drawable.sample_icon)
//        calendarDays.add(event)

        //or if you want to specify event label color
//        calendarDay.setLabelColor(Color.parseColor("#228B22"));
//        calendarDays.add(calendarDay);



    }

}
