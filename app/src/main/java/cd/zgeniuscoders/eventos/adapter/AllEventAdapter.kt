package cd.zgeniuscoders.eventos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import cd.zgeniuscoders.eventos.databinding.ItemEventListBinding
import cd.zgeniuscoders.eventos.models.Event
import com.bumptech.glide.Glide

class AllEventAdapter(val context: Context, private val events: List<Event>) : Adapter<AllEventAdapter.AllEventViewHolder>() {

    inner class AllEventViewHolder(binding: ItemEventListBinding) : ViewHolder(binding.root) {
        val binding = ItemEventListBinding.bind(binding.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllEventViewHolder {
        return AllEventViewHolder(
            ItemEventListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = events.size
    override fun onBindViewHolder(holder: AllEventViewHolder, position: Int) {
        val event = events[position]
        holder.binding.tvTitle.text = event.name
        holder.binding.tvCategory.text = event.category
        Glide.with(context).load(event.photo).into(holder.binding.img)
    }
}