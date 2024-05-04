package cd.zgeniuscoders.eventos.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cd.zgeniuscoders.eventos.adapter.CategoryAdapter
import cd.zgeniuscoders.eventos.adapter.EventAdapter
import cd.zgeniuscoders.eventos.adapter.PopularEventAdapter
import cd.zgeniuscoders.eventos.databinding.FragmentHomeBinding
import cd.zgeniuscoders.eventos.models.Category
import cd.zgeniuscoders.eventos.models.Event
import cd.zgeniuscoders.eventos.viewModel.CategoryViewModel
import cd.zgeniuscoders.eventos.viewModel.EventViewModel

class HomeFragment : Fragment(), CategoryAdapter.CategoryClickListener {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var eventViewModel: EventViewModel
    private lateinit var eventAdapter: EventAdapter

    private var eventsList: List<Event> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

//        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
//        categoryViewModel.all()
//
//        eventViewModel = ViewModelProvider(this)[EventViewModel::class.java]
//        eventViewModel.all()
//
//        eventAdapter = EventAdapter(requireContext(), eventsList)
//
//        categoryViewModel.categories.observe(viewLifecycleOwner){categories ->
//            val categoryAdapter = CategoryAdapter(requireContext(),categories, this)
//            binding.recyclerViewCategory.adapter = categoryAdapter
//        }
//
//        eventViewModel.events.observe(viewLifecycleOwner){events ->
//
//            eventsList = events
//            eventAdapter.updateEventsList(events)
//            binding.recyclerViewEvents.adapter = eventAdapter
//
//            val popularEventAdapter = PopularEventAdapter(requireContext(),events)
//            binding.recyclerViewPopularEvent.adapter = popularEventAdapter
//        }
//
//        binding.seeAllPopularEvent.setOnClickListener {
//            Intent(requireContext(), AllEventActivity::class.java).apply {
//                this.putExtra("coming", false)
//                startActivity(this)
//            }
//        }
//
//        binding.seeAllComingEvent.setOnClickListener {
//            Intent(requireContext(), AllEventActivity::class.java).apply {
//                this.putExtra("coming", true)
//                startActivity(this)
//            }
//        }
//
//        binding.btnAddEvent.setOnClickListener {
//            Intent(requireContext(), AddEventActivity::class.java).apply {
//                startActivity(this)
//            }
//        }

        return binding.root
    }

    override fun onCategoryClicked(category: Category) {
        val filteredEvents = eventsList.filter { it.category == category.name }
        eventAdapter.updateEventsList(filteredEvents)
    }

}