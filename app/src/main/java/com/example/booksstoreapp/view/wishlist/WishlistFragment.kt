package com.example.booksstoreapp.view.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksstoreapp.base.BaseFragment
import com.example.booksstoreapp.databinding.FragmentWishlistBinding
import com.example.booksstoreapp.view.adapter.BookListAdapter
import com.example.booksstoreapp.view.details.BookDetailsFragment
import com.google.android.material.snackbar.Snackbar

class WishlistFragment : BaseFragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: WishlistViewModel
    private lateinit var adapter: BookListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclerView()
        observeData()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(WishlistViewModel::class.java)
    }

    private fun setupRecyclerView() {
        adapter = BookListAdapter(
            showDescription = true,
            bookClickCallback = { book ->
                replaceFragment(BookDetailsFragment.newInstance(book.id), true)
            }
        )
        
        binding.wishlistRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@WishlistFragment.adapter
        }
    }

    private fun observeData() {
        viewModel.wishlistBooks.observe(viewLifecycleOwner) { books ->
            adapter.submitList(books)
            binding.emptyView.visibility = if (books.isEmpty()) View.VISIBLE else View.GONE
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
} 