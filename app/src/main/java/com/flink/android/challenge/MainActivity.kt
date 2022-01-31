package com.flink.android.challenge

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.flink.android.challenge.adapter.ProductAdapter
import com.flink.android.challenge.databinding.ActivityMainBinding
import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider




class MainActivity : AppCompatActivity() {



    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(application) }

    private lateinit var binding: ActivityMainBinding

    val adapter = ProductAdapter(
        callMinus = {
            Log.d("##", "Minus")
            viewModel.minus(it)
        }, callPlus = {
            Log.d("##", "Plus")
            viewModel.plus(it)
        })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerview.adapter = adapter

        viewModel.state.observe(this) {
            when (it) {
                is State.Success -> {
                    it.products.forEach { product ->
                        Log.d("##", "Product: $product")
                    }

                    adapter.setData(it.products)
                    adapter.notifyDataSetChanged()
                }
                is State.Error -> {
                    Toast.makeText(this, "Error case", Toast.LENGTH_SHORT).show()
                }
                is State.Empty -> {
                    Toast.makeText(this, "Empty product list", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }

}