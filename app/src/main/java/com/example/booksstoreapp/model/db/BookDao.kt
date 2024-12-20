package com.example.booksstoreapp.model.db

import androidx.room.*

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    suspend fun getAll(): List<Book>

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getBook(id: Int): Book?

    @Query("SELECT * FROM books WHERE volumeInfo_title LIKE '%' || :query || '%' OR volumeInfo_authors LIKE '%' || :query || '%'")
    suspend fun searchBooks(query: String): List<Book>

    @Query("SELECT * FROM books WHERE isFavorite = 1")
    suspend fun getFavorites(): List<Book>

    @Query("SELECT * FROM books WHERE isWishlist = 1")
    suspend fun getWishlist(): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<Book>)

    @Query("UPDATE books SET isFavorite = NOT isFavorite WHERE id = :bookId")
    suspend fun toggleFavorite(bookId: Int)

    @Query("UPDATE books SET isWishlist = NOT isWishlist WHERE id = :bookId")
    suspend fun toggleWishlist(bookId: Int)
} 