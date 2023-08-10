package com.example.splash.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.splash.R
import com.example.splash.databinding.FragmentCreateNotesBinding
import com.example.splash.databinding.FragmentEditNoteBinding
import com.example.splash.models.NotesEntity
import com.example.splash.viewmodel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.thebluealliance.spectrum.SpectrumPalette
import java.text.SimpleDateFormat
import java.util.*

class CreateNotesFragment : Fragment(R.layout.fragment_create_notes) {

    // TODO add animation

    private lateinit var binding: FragmentCreateNotesBinding
    private lateinit var navController: NavController
    private var color =-1
    private val viewModel: NotesViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateNotesBinding.bind(view)

     navController = Navigation.findNavController(view)

      binding.btbBack.setOnClickListener{
          navController.popBackStack()
      }
        binding.btnSave.setOnClickListener {
            addNote(it)
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
                    binding.createNotesFragment.setBackgroundColor(color)
                    binding.toolBarCreateNoteFragment.setBackgroundColor(color)
                }
            }
            bottomSheetColorPicker.show()
        }

    }


    private fun addNote(it: View) {
        val noteTitle = binding.etNoteTitle.text.toString()
        val notesDescription = binding.etNoteDescription.text.toString()
        if (noteTitle.isEmpty() || notesDescription.isEmpty()) {
            Snackbar.make(it, "Enter all the fields", Snackbar.LENGTH_SHORT).show()
        } else {
            val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
            val notesTimestamp: String = sdf.format(Date())
            viewModel.addNote(
                NotesEntity(
                    noteTitle = noteTitle,
                    notesDescription = notesDescription,
                    notesTimestamp = notesTimestamp,
                    color = color
                )
            )
            Toast.makeText(requireContext(),"Note Saved",Toast.LENGTH_SHORT).show()
                navController.popBackStack()
        }
    }


}
