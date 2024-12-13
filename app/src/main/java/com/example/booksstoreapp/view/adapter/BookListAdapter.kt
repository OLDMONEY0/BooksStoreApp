package com.example.booksstoreapp.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ant.books.R
import com.ant.books.databinding.BookItemBinding
import com.ant.books.db.Book

class BookListAdapter(
    private val showDescription: Boolean,
    private val bookClickCallback: ((Book) -> Unit)?
) : ListAdapter<Book, BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding: BookItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.book_item,
            parent,
            false
        )

        binding.showDescription = showDescription

        // Устанавливаем callback нажатия на элемент
        binding.root.setOnClickListener {
            binding.book?.let {
                bookClickCallback?.invoke(it)
            }
        }

        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }
}

class BookViewHolder(
    private val binding: BookItemBinding
) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

    fun bind(book: Book) {
        binding.book = book
        binding.executePendingBindings()
    }
}

class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}