package cd.zgeniuscoders.eventos.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import cd.zgeniuscoders.eventos.R
import cd.zgeniuscoders.eventos.databinding.ItemCategoryBinding
import cd.zgeniuscoders.eventos.models.Category

class CategoryAdapter(val context: Context,private var categories: List<Category>,private val listener: CategoryClickListener) :
    Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedCategory: Category? = null
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

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.binding.name.text = category.name

        if (selectedCategory?.name == category.name){
            holder.binding.card.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.primary))
        }else{
            holder.binding.card.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.dark_secondary))
        }

        holder.itemView.setOnClickListener {
            selectedCategory = category
            listener.onCategoryClicked(category)
            notifyDataSetChanged()
        }
    }

    interface CategoryClickListener{
        fun onCategoryClicked(category: Category)
    }
}