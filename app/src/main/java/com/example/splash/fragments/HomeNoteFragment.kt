package com.example.splash.fragments

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
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

class HomeNoteFragment : Fragment(R.layout.fragment_home_note) {
    lateinit var binding: FragmentHomeNoteBinding
    private val viewModel: NotesViewModel by viewModels()
    private lateinit var notesAdapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeNoteBinding.bind(view)
        val navController = Navigation.findNavController(view)
      //  setHasOptionsMenu(true)
        // TODO implement searchview in custom tool bar
        // TODO implement diffUtil function in recyclerview
        setUpRecyclerView()

        binding.llFabAddNote.setOnClickListener {
           navController.navigate(R.id.action_homeNoteFragment_to_createNotesFragment)
        }
        binding.fabAddNote.setOnClickListener {
            navController.navigate(R.id.action_homeNoteFragment_to_createNotesFragment)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.rvNotesList.setOnScrollChangeListener { _, scrollX, scrollY, _, oldScrollY->
                when{
                    scrollY>oldScrollY ->{
                        binding.llFabAddNote.isVisible= false
                    }
                    scrollX==oldScrollY ->{
                        binding.llFabAddNote
                    }
                }
            }
        }

    }

    private fun setUpRecyclerView() {
        binding.rvNotesList.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        // add onclick delete and onclick view
        notesAdapter = NotesAdapter(requireContext())
        binding.rvNotesList.adapter = notesAdapter

        viewModel.allNotes.observe(viewLifecycleOwner, Observer { list->
            list?.let {
                notesAdapter.updateList(it)
            }
        })
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.search_menu, menu)
//
//        val item = menu.findItem(R.id.menu_search)
//        val searchView = item.actionView as SearchView
//        searchView.queryHint = "Search Notes..."
//
//
//        searchView.setOnQueryTextListener(object: OnQueryTextListener{
//            override fun onQueryTextSubmit(nextText: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                if(newText!=null) {
//                    notesAdapter.filterList(newText)
//                }
//                return true
//            }
//
//        })
//        super.onCreateOptionsMenu(menu, inflater)
//    }

}



