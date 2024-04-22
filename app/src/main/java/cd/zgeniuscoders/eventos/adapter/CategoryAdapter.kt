package cd.zgeniuscoders.eventos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import cd.zgeniuscoders.eventos.databinding.ItemCategoryBinding
import cd.zgeniuscoders.eventos.models.Category

class CategoryAdapter(private var categories: List<Category>) :
    Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(binding: ItemCategoryBinding) : ViewHolder(binding.root) {
        val binding = ItemCategoryBinding.bind(binding.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.binding.name.text = category.name
    }

}