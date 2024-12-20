package com.example.booksstoreapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.booksstoreapp.R
import com.example.booksstoreapp.databinding.BookItemBinding
import com.example.booksstoreapp.model.db.Book

class BookListAdapter(
    private val showDescription: Boolean,
    private val bookClickCallback: ((Book) -> Unit)?
) : ListAdapter<Book, BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.showDescription = showDescription

        binding.root.setOnClickListener {
            binding.book?.let { book ->
                bookClickCallback?.invoke(book)
            }
        }

        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BookViewHolder(
    private val binding: BookItemBinding
) : RecyclerView.ViewHolder(binding.root) {

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