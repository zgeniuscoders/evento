package cd.zgeniuscoders.eventos.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import cd.zgeniuscoders.eventos.databinding.ItemEventBinding
import cd.zgeniuscoders.eventos.databinding.ItemPopularBinding
import cd.zgeniuscoders.eventos.models.Event
import cd.zgeniuscoders.eventos.views.EventActivity
import com.bumptech.glide.Glide

class PopularEventAdapter(private val context: Context, private val events: List<Event>) : Adapter<PopularEventAdapter.EventViewHolder>() {
    inner class EventViewHolder(binding: ItemPopularBinding) : ViewHolder(binding.root) {
        val binding = ItemPopularBinding.bind(binding.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            ItemPopularBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        Glide.with(context).load(event.photo).into(holder.binding.eventImage)
        holder.binding.eventTitle.text = event.name
        holder.binding.eventCategory.text = event.category


        holder.binding.event.setOnClickListener {
//            Intent(context, EventActivity::class.java).apply {
//                putExtra("id", event.name)
//                putExtra("name", event.name)
//                putExtra("description", event.description)
//                putExtra("photo", event.photo)
//                putExtra("startAt", event.startAt)
//                putExtra("endAt", event.endAt)
//                putExtra("category", event.category)
//                context.startActivity(this)
//            }
        }
    }
}