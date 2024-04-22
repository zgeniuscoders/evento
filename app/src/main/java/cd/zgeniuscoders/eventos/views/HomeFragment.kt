package cd.zgeniuscoders.eventos.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cd.zgeniuscoders.eventos.adapter.CategoryAdapter
import cd.zgeniuscoders.eventos.adapter.EventAdapter
import cd.zgeniuscoders.eventos.adapter.PopularEventAdapter
import cd.zgeniuscoders.eventos.databinding.FragmentHomeBinding
import cd.zgeniuscoders.eventos.viewModel.CategoryViewModel
import cd.zgeniuscoders.eventos.viewModel.EventViewModel

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var eventViewModel: EventViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        categoryViewModel.all()

        eventViewModel = ViewModelProvider(this)[EventViewModel::class.java]
        eventViewModel.all()

        categoryViewModel.categories.observe(viewLifecycleOwner){categories ->
            val categoryAdapter = CategoryAdapter(categories)
            binding.recyclerViewCategory.adapter = categoryAdapter
        }

        eventViewModel.events.observe(viewLifecycleOwner){events ->
            val eventAdapter = EventAdapter(requireContext(),events)
            binding.recyclerViewEvents.adapter = eventAdapter

            val popularEventAdapter = PopularEventAdapter(requireContext(),events)
            binding.recyclerViewPopularEvent.adapter = popularEventAdapter
        }

        binding.btnAddEvent.setOnClickListener {
            Intent(requireContext(), AddEventActivity::class.java).apply {
                startActivity(this)
            }
        }

        return binding.root
    }

}