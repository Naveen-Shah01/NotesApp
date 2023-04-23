package com.example.splash.fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.splash.R
import com.example.splash.databinding.FragmentEditNoteBinding
import com.example.splash.models.NotesEntity
import com.example.splash.viewmodel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {

    val notesData by navArgs<EditNoteFragmentArgs>()
    private lateinit var binding: FragmentEditNoteBinding
    private val viewModel: NotesViewModel by viewModels()
    private var color =-1
    private lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditNoteBinding.bind(view)
        setHasOptionsMenu(true)
        navController=Navigation.findNavController(view)
        setNote()

  // TODO lock the screen until edit button is not pressed
        // TODO add delete icon and functionality in toolbar
        // TODO add date in xml file

        binding.btnEdit.setOnClickListener {
            binding.btnEdit.visibility = View.GONE
            binding.btnSave.visibility = View.VISIBLE

        }
        binding.btnSave.setOnClickListener {
            updateNote(it)
        }

    }

    private fun setNote() {
        binding.etNoteTitle.setText(notesData.data.noteTitle)
        binding.etNoteDescription.setText(notesData.data.notesDescription)
    }

    private fun updateNote(it: View) {
        val noteTitle = binding.etNoteTitle.text.toString()
        val notesDescription = binding.etNoteDescription.text.toString()
        if (noteTitle.isEmpty() || notesDescription.isEmpty()) {
            Snackbar.make(it, "Enter all the fields", Snackbar.LENGTH_SHORT).show()
        } else {
            val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
            val notesTimestamp: String = sdf.format(Date())
            val updateNote =
                NotesEntity(notesData.data.id, noteTitle, notesDescription, notesTimestamp)
            viewModel.updateNote(updateNote)
            Toast.makeText(requireContext(), "Note Updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it)
                .navigate(R.id.action_editNoteFragment_to_homeNoteFragment2)
        }
    }





    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            val bottomSheet: BottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.BottomSheetStyleDelete)
            bottomSheet.setContentView(R.layout.delete_dialog)
            val tvYes = bottomSheet.findViewById<TextView>(R.id.deleteDialogYes)
            val tvNo = bottomSheet.findViewById<TextView>(R.id.deleteDialogNo)

            tvYes?.setOnClickListener {

                viewModel.deleteNote(notesData.data)

                findNavController().navigate(R.id.action_editNoteFragment_to_homeNoteFragment2)
                Toast.makeText(requireContext(), "Note Deleted", Toast.LENGTH_LONG).show()
                bottomSheet.dismiss()
            }
            tvNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}