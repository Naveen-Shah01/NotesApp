package com.example.splash.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.splash.R
import com.example.splash.adapter.NotesAdapter
import com.example.splash.databinding.FragmentHomeNoteBinding
import com.example.splash.viewmodel.NotesViewModel
import java.util.concurrent.TimeUnit

class HomeNoteFragment : Fragment(R.layout.fragment_home_note) {
    lateinit var binding: FragmentHomeNoteBinding
    private val viewModel: NotesViewModel by viewModels()
    private lateinit var notesAdapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeNoteBinding.bind(view)
        val navController = Navigation.findNavController(view)

        // 2 . color added in RecyclerView NotesAdapter
        setUpRecyclerView()

        binding.llFabAddNote.setOnClickListener {
            navController.navigate(R.id.action_homeNoteFragment_to_createNotesFragment)
        }
        binding.fabAddNote.setOnClickListener {
            navController.navigate(R.id.action_homeNoteFragment_to_createNotesFragment)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.rvNotesList.setOnScrollChangeListener { _, scrollX, scrollY, _, oldScrollY ->
                when {
                    scrollY > oldScrollY -> {
                        binding.tvAddNoteFab.isVisible = false
                    }
                    scrollX == oldScrollY -> {
                        binding.tvAddNoteFab.isVisible = true
                    }
                    else -> {
                        binding.tvAddNoteFab.isVisible = true
                    }
                }
            }
        }

    }

    private fun setUpRecyclerView() {
        binding.rvNotesList.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        // add onclick delete and onclick view
        notesAdapter = NotesAdapter(requireContext())
        binding.rvNotesList.adapter = notesAdapter


        // Transition Added in recyclerView
        binding.rvNotesList.apply {
            postponeEnterTransition(300L, TimeUnit.MILLISECONDS)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }

        viewModel.allNotes.observe(viewLifecycleOwner, Observer { list ->
            if (list.isEmpty()) {

                binding.noData.visibility = View.VISIBLE
            } else {
                binding.noData.visibility= View.GONE
                notesAdapter.updateList(list)
            }
        })
    }


}
