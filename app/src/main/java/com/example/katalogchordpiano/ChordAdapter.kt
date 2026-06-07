package com.example.katalogchordpiano

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class ChordAdapter(context: Context, private val chords: List<Chord>) :
    ArrayAdapter<Chord>(context, 0, chords) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_chord, parent, false)
        }

        val chord = chords[position]

        val ivIcon = itemView!!.findViewById<ImageView>(R.id.ivChordIcon)
        val tvName = itemView.findViewById<TextView>(R.id.tvChordName)
        val tvNotes = itemView.findViewById<TextView>(R.id.tvChordNotes)
        val tvTag = itemView.findViewById<TextView>(R.id.tvTypeTag)

        tvName.text = chord.nama
        tvNotes.text = chord.notes
        tvTag.text = chord.type
        ivIcon.setImageResource(chord.image)

        if (chord.type.lowercase() == "major") {
            tvTag.setBackgroundResource(R.drawable.tag_bg_major)
            tvTag.setTextColor(ContextCompat.getColor(context, R.color.major_text))
        } else {
            tvTag.setBackgroundResource(R.drawable.tag_bg_minor)
            tvTag.setTextColor(ContextCompat.getColor(context, R.color.minor_text))
        }

        return itemView
    }
}