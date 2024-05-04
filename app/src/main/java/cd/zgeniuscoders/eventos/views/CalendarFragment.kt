package cd.zgeniuscoders.eventos.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cd.zgeniuscoders.eventos.adapter.CalendarAdapter
import cd.zgeniuscoders.eventos.databinding.FragmentCalendarBinding
import cd.zgeniuscoders.eventos.models.Event
import java.time.LocalDate
import java.time.ZoneId

class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentCalendarBinding
    private lateinit var calendarRecycler: RecyclerView
    private lateinit var calendarView: CalendarView
    private lateinit var calendarAdapter: CalendarAdapter

    private var events: List<Event> = listOf(
        Event(
            id = "event1",
            userId = "user1",
            name = "Meeting",
            description = "Important meeting",
            category = "Work",
            photo = "https://example.com/meeting.png",
            startAt = LocalDate.now(), // Replace with actual timestamp
            endAt = LocalDate.now(), // Replace with actual timestamp
        ),
        Event(
            id = "event2",
            userId = "user2",
            name = "Birthday Party",
            description = "Celebrating John's birthday",
            category = "Personal",
            photo = "https://example.com/birthday.jpg",
            startAt = LocalDate.now().plusMonths(1), // Replace with actual timestamp
            endAt = LocalDate.now().plusMonths(2), // Replace with actual timestamp
        )
    )
    private var selectedDate: LocalDate = LocalDate.now()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(layoutInflater)

        calendarRecycler = binding.calendarRecyclerView
        calendarView = binding.calendarView

        calendarAdapter = CalendarAdapter(events) { date ->
            selectedDate = date
            // Handle selected date (e.g., display event details)
            updateRecyclerViewData(selectedDate) // Update RecyclerView based on selected month/year
        }


        calendarRecycler.layoutManager = GridLayoutManager(requireContext(), 7)
        calendarRecycler.adapter = calendarAdapter


        // Set initial calendar view date and handle selection
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedMonth = month + 1 // Months are 0-indexed in CalendarView
            updateCalendarData(year, selectedMonth) // Update data based on selected month/year
            updateRecyclerViewData(LocalDate.of(year, selectedMonth, dayOfMonth))
        }


        // Update data initially based on current date
        val now = LocalDate.now()
        updateCalendarData(now.year, now.monthValue)
        updateRecyclerViewData(now)

        return binding.root
    }

    private fun updateCalendarData(year: Int, month: Int) {
        // Logic to fetch events for the specified month/year and update adapter data
        val events = fetchEventsForMonth(year, month)

        // Update adapter data with the fetched events
        // Assuming you have an adapter named 'calendarAdapter'
        calendarAdapter.submitList(events)
    }

    // Placeholder function for fetching events (replace with your actual event fetching logic)
    private fun fetchEventsForMonth(year: Int, month: Int): List<Event> {
        // This is a placeholder function that simulates fetching events.
        // You will need to replace this with your actual event fetching logic,
        // which might involve querying a database, API, or other data source.

        // Sample event data using your Event model
        val eventData: List<Event> = listOf(
            Event(
                id = "event1",
                userId = "user1",
                name = "Meeting",
                description = "Important meeting",
                category = "Work",
                photo = "https://example.com/meeting.png",
                startAt = LocalDate.now(), // Replace with actual timestamp
                endAt = LocalDate.now(), // Replace with actual timestamp
            ),
            Event(
                id = "event2",
                userId = "user2",
                name = "Birthday Party",
                description = "Celebrating John's birthday",
                category = "Personal",
                photo = "https://example.com/birthday.jpg",
                startAt = LocalDate.now().plusMonths(1), // Replace with actual timestamp
                endAt = LocalDate.now().plusMonths(2), // Replace with actual timestamp
            )
        )

        // Filter events for the specified month
        return eventData.filter { event -> event.startAt.monthValue == month }
    }


    private fun updateRecyclerViewData(selectedDate: LocalDate) {
        // Filter events based on selected month/year and update adapter
        val adapter = calendarRecycler.adapter as CalendarAdapter
        adapter.submitList(filterEventsForMonthYear(selectedDate))
    }

    private fun filterEventsForMonthYear(selectedDate: LocalDate): List<Event> {
        return events.filter { event -> event.startAt.year == selectedDate.year && event.startAt.month == selectedDate.month }
    }
}
