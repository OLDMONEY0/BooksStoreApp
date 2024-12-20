package com.example.booksstoreapp.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.booksstoreapp.base.BaseFragment
import com.example.booksstoreapp.databinding.FragmentBookDetailsBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class BookDetailsFragment : BaseFragment() {
    private var _binding: FragmentBookDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BookDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBookDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupClickListeners()
        observeData()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(BookDetailsViewModel::class.java)
        arguments?.getInt(ARG_BOOK_ID)?.let { bookId ->
            viewModel.loadBook(bookId)
        }
    }

    private fun setupClickListeners() {
        binding.favoriteButton.setOnClickListener {
            viewModel.toggleFavorite()
        }
        
        binding.wishlistButton.setOnClickListener {
            viewModel.toggleWishlist()
        }
    }

    private fun observeData() {
        viewModel.book.observe(viewLifecycleOwner) { book ->
            binding.titleText.text = book.volumeInfo.title
            binding.descriptionText.text = book.volumeInfo.description
            Picasso.get().load(book.volumeInfo.imageLinks?.thumbnail).into(binding.bookImage)
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_BOOK_ID = "book_id"

        fun newInstance(bookId: Int) = BookDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_BOOK_ID, bookId)
            }
        }
    }
} 