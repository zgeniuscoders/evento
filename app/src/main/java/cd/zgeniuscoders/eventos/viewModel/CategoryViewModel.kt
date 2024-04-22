package cd.zgeniuscoders.eventos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cd.zgeniuscoders.eventos.models.Category
import cd.zgeniuscoders.eventos.repository.CategoryRepository

class CategoryViewModel : ViewModel() {

    private var categoryRepository = CategoryRepository()

    private val _categories = MutableLiveData<List<Category>>()
    private val _category = MutableLiveData<Category>()

    val categories: LiveData<List<Category>> = _categories

    fun all(){
        categoryRepository.getAllCategories {
            _categories.postValue(it)
        }
    }

}