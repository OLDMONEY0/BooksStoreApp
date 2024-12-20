package com.example.booksstoreapp.view.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksstoreapp.model.db.Book
import com.example.booksstoreapp.repository.BookRepository
import com.example.booksstoreapp.util.Injection
import kotlinx.coroutines.launch

class BookDetailsViewModel(application: Application) : ViewModel() {
    private val repository: BookRepository = Injection.provideBookRepository(application)

    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> = _book

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private var currentBookId: Int? = null

    fun loadBook(bookId: Int) {
        currentBookId = bookId
        viewModelScope.launch {
            try {
                _book.value = repository.getBook(bookId)
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun toggleFavorite() {
        currentBookId?.let { bookId ->
            viewModelScope.launch {
                try {
                    repository.toggleFavorite(bookId)
                    loadBook(bookId)
                } catch (e: Exception) {
                    _error.value = e.message
                }
            }
        }
    }

    fun toggleWishlist() {
        currentBookId?.let { bookId ->
            viewModelScope.launch {
                try {
                    repository.toggleWishlist(bookId)
                    loadBook(bookId)
                } catch (e: Exception) {
                    _error.value = e.message
                }
            }
        }
    }
} 