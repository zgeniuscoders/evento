package cd.zgeniuscoders.eventos.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import cd.zgeniuscoders.eventos.R
import cd.zgeniuscoders.eventos.databinding.ActivityAddEventBinding
import cd.zgeniuscoders.eventos.viewModel.EventViewModel

class AddEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEventBinding
    private lateinit var eventViewModel: EventViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddEventBinding.inflate(layoutInflater)
        eventViewModel = ViewModelProvider(this)[EventViewModel::class.java]

        val title = binding.edtName.text
        val description = binding.edtDescription.text
        val startAt = binding.edtStartAt.text
        val endAt = binding.edtStartAt.text

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//        eventViewModel.create(title,description,startAt,endAt)

    }
}