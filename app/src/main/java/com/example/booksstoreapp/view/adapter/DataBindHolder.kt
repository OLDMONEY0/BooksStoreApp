package com.example.booksstoreapp.view.adapter


import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView


class DataBindHolder<out T : ViewDataBinding> constructor(val binding: T) :
    RecyclerView.ViewHolder(binding.root)