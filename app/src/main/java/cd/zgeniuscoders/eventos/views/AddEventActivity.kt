package cd.zgeniuscoders.eventos.views

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import cd.zgeniuscoders.eventos.R
import cd.zgeniuscoders.eventos.databinding.ActivityAddEventBinding
import cd.zgeniuscoders.eventos.models.Event
import cd.zgeniuscoders.eventos.utilities.generateKey
import cd.zgeniuscoders.eventos.utilities.getCurrentUser
import cd.zgeniuscoders.eventos.viewModel.CategoryViewModel
import cd.zgeniuscoders.eventos.viewModel.EventViewModel
import cd.zgeniuscoders.eventos.viewModel.UploadViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEventBinding
    private lateinit var eventViewModel: EventViewModel
    private lateinit var uploadViewModel: UploadViewModel

    private lateinit var titleEdt: TextInputEditText
    private lateinit var descriptionEdt: TextInputEditText
    private lateinit var startAtEdt: TextInputEditText
    private lateinit var endAtEdt: TextInputEditText

    private lateinit var titleLayout: TextInputLayout
    private lateinit var descriptionLayout: TextInputLayout
    private lateinit var startAtLayout: TextInputLayout
    private lateinit var endAtLayout: TextInputLayout

    lateinit var categoryList: ArrayList<String>

    private var coverImage: Uri? = null
    private var hasError = true

    private var launchGalleryActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            coverImage = it.data!!.data
            binding.img.setImageURI(coverImage)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddEventBinding.inflate(layoutInflater)
        eventViewModel = ViewModelProvider(this)[EventViewModel::class.java]
        uploadViewModel = ViewModelProvider(this)[UploadViewModel::class.java]

        setContentView(binding.root)
        initialiseFieldVar()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getCategoryList()

        binding.img.setOnClickListener {
            val intent = Intent("android.intent.action.GET_CONTENT")
            intent.type = "image/*"
            launchGalleryActivity.launch(intent)
        }

        binding.btnPublish.setOnClickListener {
            val title = titleEdt.text.toString()
            val description = descriptionEdt.text.toString()
            val startAt = startAtEdt.text.toString()
            val endAt = endAtEdt.text.toString()

            validateField()

            if (!hasError) {

                binding.btnPublish.isEnabled = false

                uploadViewModel.uploadImage(this, coverImage!!, "path")
                uploadViewModel.error.observe(this) {
                    Toast.makeText(this, it, Toast.LENGTH_LONG)
                }
                uploadViewModel.imageUrl.observe(this) { imgUrl ->

                    val event = Event(
                        generateKey("events"),
                        getCurrentUser()!!.uid,
                        title,
                        description,
                        categoryList[binding.eventCategory.selectedItemPosition],
                        imgUrl,
                        startAt,
                        endAt
                    )

                    eventViewModel.create(event)
                    eventViewModel.isCreated.observe(this) {
                        binding.btnPublish.isEnabled = true
                        if (it) {
                            Toast.makeText(this, "event added succefuly", Toast.LENGTH_LONG)
                            emptyFields()
                        }
                    }

                }

            }
        }


    }

    private fun getCategoryList() {
        categoryList = ArrayList()
        val categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        categoryViewModel.all()
        categoryViewModel.categories.observe(this){categories ->

            categoryList.add(0, getString(R.string.select_a_category))
            for(cat in categories){
                categoryList.add(cat.name)
            }

            val arrayAdapter = ArrayAdapter(this, R.layout.item_dropdown, categoryList)
            binding.eventCategory.adapter = arrayAdapter
        }
    }

    private fun emptyFields() {
        titleEdt.setText("")
        descriptionEdt.setText("")
        startAtEdt.setText("")
        endAtEdt.setText("")
        categoryList[0]
    }

    private fun initialiseFieldVar() {
        titleEdt = binding.edtName
        descriptionEdt = binding.edtDescription
        startAtEdt = binding.edtStartAt
        endAtEdt = binding.edtEndsAt

        titleLayout = binding.edtNameLayout
        descriptionLayout = binding.edtDescriptionLayout
        startAtLayout = binding.edtStartAtLayout
        endAtLayout = binding.edtEndsAtLayout
    }

    private fun validateField() {
        if (titleEdt.text!!.isEmpty()) {
            hasError = true
            titleLayout.error = "This field cannot be empty"
            titleLayout.isErrorEnabled = true
        } else {
            hasError = false
            titleLayout.error = null
            titleLayout.isErrorEnabled = false
        }

        if (descriptionEdt.text!!.isEmpty()) {
            hasError = true
            descriptionLayout.error = "This field cannot be empty"
            descriptionLayout.isErrorEnabled = true
        } else {
            hasError = false
            descriptionLayout.error = null
            descriptionLayout.isErrorEnabled = false
        }

        if (startAtEdt.text!!.isEmpty()) {
            hasError = true
            startAtLayout.error = "This field cannot be empty"
            startAtLayout.isErrorEnabled = true
        } else {
            hasError = false
            startAtLayout.error = null
            startAtLayout.isErrorEnabled = false
        }

        if (endAtEdt.text!!.isEmpty()) {
            hasError = true
            endAtLayout.error = "This field cannot be empty"
            endAtLayout.isErrorEnabled = true
        } else {
            hasError = false
            endAtLayout.error = null
            endAtLayout.isErrorEnabled = false
        }

        if (categoryList[binding.eventCategory.selectedItemPosition] == getString(R.string.select_a_category)){
            hasError = true
            Toast.makeText(this, "Please select a category", Toast.LENGTH_LONG).show()
        }else{
            hasError = false
        }

        if (coverImage == null) {
            hasError = true
            Toast.makeText(this, "Please select an image", Toast.LENGTH_LONG).show()
        } else {
            hasError = false
        }
    }
}