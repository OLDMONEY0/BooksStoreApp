package com.example.booksstoreapp.view.wishlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.booksstoreapp.model.db.Book
import com.example.booksstoreapp.repository.BookRepository
import com.example.booksstoreapp.util.Injection
import kotlinx.coroutines.launch

class WishlistViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BookRepository = Injection.provideBookRepository(application)

    private val _wishlistBooks = MutableLiveData<List<Book>>()
    val wishlistBooks: LiveData<List<Book>> = _wishlistBooks

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadWishlist()
    }

    private fun loadWishlist() {
        viewModelScope.launch {
            try {
                _wishlistBooks.value = repository.getWishlist()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun toggleWishlist(bookId: Int) {
        viewModelScope.launch {
            try {
                repository.toggleWishlist(bookId)
                loadWishlist()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
} 