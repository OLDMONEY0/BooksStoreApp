package com.example.booksstoreapp.repository

import com.example.booksstoreapp.api.BookService
import com.example.booksstoreapp.model.db.Book
import com.example.booksstoreapp.model.db.BookDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository(
    private val bookService: BookService,
    private val bookDao: BookDao
) {
    suspend fun getBooks(): List<Book> = withContext(Dispatchers.IO) {
        try {
            val response = bookService.getBooks()
            val books = response.books
            bookDao.insertAll(books)
            books
        } catch (e: Exception) {
            bookDao.getAll()
        }
    }

    suspend fun searchBooks(query: String): List<Book> = withContext(Dispatchers.IO) {
        try {
            val response = bookService.searchBooks(query)
            response.books
        } catch (e: Exception) {
            bookDao.searchBooks("%$query%")
        }
    }

    suspend fun getFavorites(): List<Book> = withContext(Dispatchers.IO) {
        bookDao.getFavorites()
    }

    suspend fun getWishlist(): List<Book> = withContext(Dispatchers.IO) {
        bookDao.getWishlist()
    }

    suspend fun toggleFavorite(bookId: Int) = withContext(Dispatchers.IO) {
        bookDao.toggleFavorite(bookId)
    }

    suspend fun toggleWishlist(bookId: Int) = withContext(Dispatchers.IO) {
        bookDao.toggleWishlist(bookId)
    }

    suspend fun getBook(id: Int): Book = withContext(Dispatchers.IO) {
        try {
            val remoteBook = bookService.getBook(id.toString())
            bookDao.insert(remoteBook)
            remoteBook
        } catch (e: Exception) {
            bookDao.getBook(id) ?: throw Exception("Book not found")
        }
    }
} 