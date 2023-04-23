package com.example.splash.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.splash.databinding.ItemsNotesBinding
import com.example.splash.fragments.HomeNoteFragmentDirections
import com.example.splash.models.NotesEntity

class NotesAdapter(val context: Context) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemsNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvNoteTitle = binding.tvNoteTitle
        val tvTimeStamp = binding.tvTimeStamp
        val tvDescription = binding.tvNoteDescription
        val noteItemBackground = binding.llNoteItemLayout

    }

    private var notesList = ArrayList<NotesEntity>()
    private var allNotesList = ArrayList<NotesEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemsNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val allNotes = notesList[position]

        holder.tvNoteTitle.text = allNotes.noteTitle
        holder.tvTimeStamp.text = allNotes.notesTimestamp
        holder.tvDescription.text = allNotes.notesDescription
holder.noteItemBackground.setBackgroundColor(allNotes.color)

        // TODO implement if black background is there then change the textcolor
        holder.binding.root.setOnClickListener {
            val action =
                HomeNoteFragmentDirections.actionHomeNoteFragmentToEditNoteFragment2(allNotes)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun filterList(search: String) {
        notesList.clear()
        for (i in allNotesList) {
            if (i.noteTitle.lowercase().contains(search.lowercase()) ||
                i.notesDescription.lowercase() .contains(search.lowercase())
            ) {
                notesList.add(i)
            }
        }
        notifyDataSetChanged()
    }


    fun updateList(newsList: List<NotesEntity>) {
        allNotesList.clear()
        allNotesList.addAll(newsList)
        notesList.clear()
        notesList.addAll(allNotesList)
        notifyDataSetChanged()
    }

}

















