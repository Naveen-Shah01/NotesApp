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
import com.thebluealliance.spectrum.SpectrumPalette
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
 
        navController=Navigation.findNavController(view)
        setNote()


        binding.btnBack.setOnClickListener{
            navController.popBackStack()
        }
        binding.btnEdit.setOnClickListener {
            updateNote(it)
        }
        binding.fabColorPicker.setOnClickListener {
            val bottomSheetColorPicker: BottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.BottomSheetStyleColorPicker)
            bottomSheetColorPicker.setContentView(R.layout.color_picker)


            val colorPicker = bottomSheetColorPicker.findViewById<SpectrumPalette>(R.id.colorPicker)
            colorPicker?.apply {
                setSelectedColor(color)
                setOnColorSelectedListener {
                        value->
                    color = value
                    binding.editNotesFragment.setBackgroundColor(color)
                    binding.toolBarEditNoteFragment.setBackgroundColor(color)
                }
            }

            bottomSheetColorPicker.show()
        }

    }

    private fun setNote() {
        binding.etNoteTitle.setText(notesData.data.noteTitle)
        binding.etNoteDescription.setText(notesData.data.notesDescription)
        binding.etTimeStamp.setText(notesData.data.notesTimestamp)
       
        color = notesData.data.color
        binding.editNotesFragment.setBackgroundColor(color)
        binding.toolBarEditNoteFragment.setBackgroundColor(color)
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
                NotesEntity(notesData.data.id, noteTitle, notesDescription, notesTimestamp,color)
            viewModel.updateNote(updateNote)
            Toast.makeText(requireContext(), "Note Updated", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }
    }
    
}
