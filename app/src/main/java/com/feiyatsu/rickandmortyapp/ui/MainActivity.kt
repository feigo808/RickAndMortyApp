package com.feiyatsu.rickandmortyapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feiyatsu.rickandmortyapp.databinding.ActivityMainBinding
import com.feiyatsu.rickandmortyapp.network.data.NetworkResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: CharacterViewModel by viewModels()
    private val characterAdapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setRecyclerView()
        viewModel.fetchCharacters()
        observeViewModel()
    }

    private fun setRecyclerView() {
        binding.rvCharacters.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = characterAdapter
            setHasFixedSize(true)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (recyclerView.canScrollVertically(1)
                            .not() && newState == RecyclerView.SCROLL_STATE_IDLE
                    ) {
                        viewModel.fetchCharacters()
                    }
                }
            })
        }
    }

    private fun observeViewModel() {
        viewModel.characters.observe(this, Observer {
            when (it) {
                is NetworkResource.Success -> {
                    binding.pbCharacters.visibility = View.GONE
                    characterAdapter.addCharacters(it.data)
                }
                is NetworkResource.Error -> {
                    binding.pbCharacters.visibility = View.GONE
                    Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show()
                }
                is NetworkResource.Loading -> {
                    binding.pbCharacters.visibility = View.VISIBLE
                }
            }
        })
    }
}