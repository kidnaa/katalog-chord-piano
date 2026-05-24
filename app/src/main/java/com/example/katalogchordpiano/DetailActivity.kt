package com.example.katalogchordpiano

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    lateinit var tvTitle: TextView
    lateinit var tvNotes: TextView
    lateinit var tvType: TextView
    lateinit var imgChord: ImageView

    lateinit var btnNext: ImageButton
    lateinit var btnPrev: ImageButton

    private val chordList = arrayListOf(

        Chord("C Major","C - E - G","Major",R.drawable.c_major),
        Chord("D Major","D - F# - A","Major",R.drawable.d_major),
        Chord("E Major","E - G# - B","Major",R.drawable.e_major),
        Chord("F Major","F - A - C","Major",R.drawable.f_major),
        Chord("G Major","G - B - D","Major",R.drawable.g_major),
        Chord("A Major","A - C# - E","Major",R.drawable.a_major),
        Chord("B Major","B - D# - F#","Major",R.drawable.b_major),

        Chord("C Minor","C - D# - G","Minor",R.drawable.c_minor),
        Chord("D Minor","D - F - A","Minor",R.drawable.d_minor),
        Chord("E Minor","E - G - B","Minor",R.drawable.e_minor),
        Chord("F Minor","F - G# - C","Minor",R.drawable.f_minor),
        Chord("G Minor","G - A# - D","Minor",R.drawable.g_minor),
        Chord("A Minor","A - C - E","Minor",R.drawable.a_minor),
        Chord("B Minor","B - D - F#","Minor",R.drawable.b_minor)

    )

    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        tvTitle = findViewById(R.id.tvTitle)
        tvNotes = findViewById(R.id.tvNotes)
        tvType = findViewById(R.id.tvType)
        imgChord = findViewById(R.id.imgChord)

        btnPrev = findViewById(R.id.btnPrev)
        btnNext = findViewById(R.id.btnNext)

        index =
            intent.getIntExtra(
                "INDEX",
                0
            )

        tampilChord()

        btnNext.setOnClickListener {

            if (
                index <
                chordList.lastIndex
            ) {

                index++

                tampilChord()

            }

        }

        btnPrev.setOnClickListener {

            if (
                index >
                0
            ) {

                index--

                tampilChord()

            }

        }

    }

    private fun tampilChord() {

        val chord =
            chordList[index]

        tvTitle.text =
            chord.nama

        tvNotes.text =
            "Notes: ${chord.notes}"

        tvType.text =
            "Type: ${chord.type}"

        imgChord.setImageResource(
            chord.image
        )

    }

}