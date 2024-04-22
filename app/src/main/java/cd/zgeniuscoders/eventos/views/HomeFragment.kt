package cd.zgeniuscoders.eventos.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cd.zgeniuscoders.eventos.adapter.CategoryAdapter
import cd.zgeniuscoders.eventos.databinding.FragmentHomeBinding
import cd.zgeniuscoders.eventos.viewModel.CategoryViewModel

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var categoryViewModel: CategoryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        categoryViewModel.all()

        categoryViewModel.categories.observe(viewLifecycleOwner){categories ->
            val categoryAdapter = CategoryAdapter(categories)
            binding.recyclerViewCategory.adapter = categoryAdapter
        }


        return binding.root
    }

}