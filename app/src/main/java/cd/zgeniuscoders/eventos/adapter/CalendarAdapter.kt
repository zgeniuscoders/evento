package cd.zgeniuscoders.eventos.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import cd.zgeniuscoders.eventos.R
import cd.zgeniuscoders.eventos.databinding.ItemDateBinding
import cd.zgeniuscoders.eventos.models.Event
import java.time.LocalDate

class CalendarAdapter(
    private var events: List<Event>,
    private val onDateSelected: (LocalDate) -> Unit
) : Adapter<CalendarAdapter.CalendarViewHolder>() {

    private var selectedDate: LocalDate? = null

    inner class CalendarViewHolder(binding: ItemDateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val binding = ItemDateBinding.bind(binding.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        return CalendarViewHolder(
            ItemDateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date = calculateDateForPosition(position)
        holder.binding.date.text = date.dayOfMonth.toString()

        // Highlight date with event
        if (events.any { it.startAt == date }) {
            holder.itemView.setBackgroundColor(Color.RED)
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        }

        Log.i("EVENTS", events.toString())

        // Handle click events
        holder.itemView.setOnClickListener {
            selectedDate = date
            onDateSelected(selectedDate!!)

            Log.i("DATESELECTED", selectedDate.toString())
            // Update UI to reflect selection (optional)
        }
    }

    override fun getItemCount(): Int {
        val now = LocalDate.now()
        val year = now.year
        val month = now.month
        return LocalDate.of(year, month,1).lengthOfMonth()
    }


    private fun calculateDateForPosition(position: Int): LocalDate {
        val daysPerRow = 7

        val rows = position / daysPerRow
        val daysInCurrentMonth = position % daysPerRow + 1
        val baseDate = LocalDate.now()
        val currentDate = baseDate.withDayOfMonth(1)
        return currentDate.plusDays((daysInCurrentMonth - 1).toLong()).plusWeeks(rows.toLong())
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(eventsList: List<Event>) {
        events = eventsList
        notifyDataSetChanged()
    }

}
