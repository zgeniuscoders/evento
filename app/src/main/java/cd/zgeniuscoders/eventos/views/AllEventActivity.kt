package cd.zgeniuscoders.eventos.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import cd.zgeniuscoders.eventos.R
import cd.zgeniuscoders.eventos.adapter.AllEventAdapter
import cd.zgeniuscoders.eventos.adapter.CategoryAdapter
import cd.zgeniuscoders.eventos.databinding.ActivityAllEventBinding
import cd.zgeniuscoders.eventos.models.Category
import cd.zgeniuscoders.eventos.models.Event
import cd.zgeniuscoders.eventos.viewModel.CategoryViewModel
import cd.zgeniuscoders.eventos.viewModel.EventViewModel

class AllEventActivity : AppCompatActivity(), CategoryAdapter.CategoryClickListener {
    private lateinit var binding: ActivityAllEventBinding
    private lateinit var eventViewModel: EventViewModel
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var allEventAdapter: AllEventAdapter

    private var eventsList: List<Event> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAllEventBinding.inflate(layoutInflater)
        eventViewModel = ViewModelProvider(this)[EventViewModel::class.java]

        val isComing = intent.getBooleanExtra("coming", false)

        setContentView(binding.root)

        allEventAdapter = AllEventAdapter(this, eventsList)

        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        categoryViewModel.all()

        categoryViewModel.categories.observe(this){categories ->
            val categoryAdapter = CategoryAdapter(this,categories, this)
            binding.recyclerViewCategory.adapter = categoryAdapter
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        eventViewModel.all()
        eventViewModel.events.observe(this) { events ->

            eventsList = events
            allEventAdapter.updateEventsList(events)
            binding.recyclerViewEvents.adapter = allEventAdapter
        }

    }

    override fun onCategoryClicked(category: Category) {
        val filteredEvents = eventsList.filter { it.category == category.name }
            allEventAdapter.updateEventsList(filteredEvents)
    }
}