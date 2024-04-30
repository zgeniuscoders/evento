package cd.zgeniuscoders.eventos.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cd.zgeniuscoders.eventos.R
import cd.zgeniuscoders.eventos.databinding.ActivityEventBinding
import com.bumptech.glide.Glide

class EventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val eventName = intent.getStringExtra("name")
        val eventCategory = intent.getStringExtra("category")
        val eventDescription = intent.getStringExtra("description")
        val eventStartAt = intent.getStringExtra("startAt")
        val eventEndAt = intent.getStringExtra("endAt")
        val eventId = intent.getStringExtra("id")
        val eventPhoto = intent.getStringExtra("photo")

        binding.eventName.text = eventName
        binding.eventDescription.text = eventDescription
        binding.eventDate.text = eventStartAt

        Glide.with(this).load(eventPhoto).into(binding.eventCoverImg)
    }
}