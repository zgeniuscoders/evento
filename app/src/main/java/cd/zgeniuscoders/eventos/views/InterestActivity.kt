package cd.zgeniuscoders.eventos.views

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import cd.zgeniuscoders.eventos.R
import cd.zgeniuscoders.eventos.databinding.ActivityInterestBinding
import cd.zgeniuscoders.eventos.viewModel.InterestViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class InterestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInterestBinding
    private lateinit var interestViewModel: InterestViewModel

    private val interests = ArrayList<String>()
    private val interestsApp: ArrayList<String> = arrayListOf(
        "Travel",
        "Movies",
        "Music",
        "Sports",
        "Cooking",
        "Reading",
        "Art",
        "Photography",
        "Pets",
        "Theatre",
        "Fashion",
        "Technology",
        "Video Games",
        "Dancing",
        "Nature",
        "Science",
        "Fitness",
        "Adventure",
        "Writing",
        "Humanitarian"
    )

    private fun createChip(name: String) {

        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_selected),
                intArrayOf()
            ),
            intArrayOf(
                ContextCompat.getColor(this, R.color.primary),
                ContextCompat.getColor(this, R.color.gray)
            )
        )

        val chip = Chip(this)
        chip.apply {
            text = name
            isCheckedIconVisible = false
            isClickable = true
            isCheckable = true
            chipBackgroundColor = colorStateList
            binding.apply {
                chipEntryGroup.addView(chip as View)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityInterestBinding.inflate(layoutInflater)
        interestViewModel = ViewModelProvider(this)[InterestViewModel::class.java]

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        for (chip in interestsApp) {
            createChip(chip)
        }

        val chip: ChipGroup = binding.chipEntryGroup
        chip.setOnCheckedStateChangeListener { group, checkedIds ->
            try {
                val checkedId = checkedIds.last()
                val current: Chip? = group.findViewById(checkedId)
                if (current != null) {
                    val text = current.text.toString()
                    if (!interests.contains(text)) {
                        interests.add(text)
                    } else {
                        interests.remove(text)
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this, "empty", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnNext.setOnClickListener {
            interestViewModel.addInterest(interests)
            interestViewModel.isUpdated.observe(this) { isUpdated ->
                if (isUpdated) {
                    Intent(this, MainActivity::class.java).apply {
                        startActivity(this)
                        finish()
                    }
                }
            }
        }
    }

}