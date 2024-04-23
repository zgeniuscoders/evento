package cd.zgeniuscoders.eventos.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import cd.zgeniuscoders.eventos.R
import cd.zgeniuscoders.eventos.adapter.AllEventAdapter
import cd.zgeniuscoders.eventos.databinding.ActivityAllEventBinding
import cd.zgeniuscoders.eventos.viewModel.EventViewModel

class AllEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllEventBinding
    private lateinit var eventViewModel: EventViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAllEventBinding.inflate(layoutInflater)
        eventViewModel = ViewModelProvider(this)[EventViewModel::class.java]

        val isComing = intent.getBooleanExtra("coming", false)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        eventViewModel.all()
        eventViewModel.events.observe(this) { events ->
            val eventAdapter = AllEventAdapter(this, events)
            binding.recyclerViewEvents.adapter = eventAdapter
        }

    }
}