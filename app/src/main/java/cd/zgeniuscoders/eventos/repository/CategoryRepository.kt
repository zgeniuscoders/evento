package cd.zgeniuscoders.eventos.repository

import cd.zgeniuscoders.eventos.models.Category

class CategoryRepository: Repository() {
    override val collection = "categories"

    fun getAllCategories(callback: (List<Category>) -> Unit){
        all().addSnapshotListener { querySnap, error ->
            if(error != null){
                callback(emptyList())
            }

            if(querySnap != null){
                val categories = mutableListOf<Category>()
                for (doc in querySnap.documents){
                    val category = doc.toObject(Category::class.java)
                    categories.add(category!!)
                }
                callback(categories)
            }
        }
    }
}