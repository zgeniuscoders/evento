package cd.zgeniuscoders.eventos.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import cd.zgeniuscoders.eventos.databinding.ItemEventBinding
import cd.zgeniuscoders.eventos.models.Event
import com.bumptech.glide.Glide

class EventAdapter(private val context: Context,private var events: List<Event>) : Adapter<EventAdapter.EventViewHolder>() {
    inner class EventViewHolder(binding: ItemEventBinding) : ViewHolder(binding.root) {
        val binding = ItemEventBinding.bind(binding.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            ItemEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        Glide.with(context).load(event.photo).into(holder.binding.imageView2)
        holder.binding.eventTitle.text = event.name
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateEventsList(newEvents: List<Event>) {
        events = newEvents
        notifyDataSetChanged()
    }
}