package com.example.katalogchordpiano

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

class DetailActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvNotes: TextView
    private lateinit var tvType: TextView
    private lateinit var imgChord: ImageView
    private lateinit var btnBack: ImageButton
    private lateinit var btnNext: ImageButton
    private lateinit var btnPrev: ImageButton
    private lateinit var btnPlay: MaterialButton

    private var mediaPlayer: MediaPlayer? = null

    private val chordList = arrayListOf(
        // MAJOR
        Chord("C Major", "C - E - G", "Major", R.drawable.c_major, R.raw.c_major),
        Chord("D Major", "D - F# - A", "Major", R.drawable.d_major, R.raw.d_major),
        Chord("E Major", "E - G# - B", "Major", R.drawable.e_major, R.raw.e_major),
        Chord("F Major", "F - A - C", "Major", R.drawable.f_major, R.raw.f_major),
        Chord("G Major", "G - B - D", "Major", R.drawable.g_major, R.raw.g_major),
        Chord("A Major", "A - C# - E", "Major", R.drawable.a_major, R.raw.a_major),
        Chord("B Major", "B - D# - F#", "Major", R.drawable.b_major, R.raw.b_major),

        // MINOR
        Chord("C Minor", "C - D# - G", "Minor", R.drawable.c_minor, R.raw.c_minor),
        Chord("D Minor", "D - F - A", "Minor", R.drawable.d_minor, R.raw.d_minor),
        Chord("E Minor", "E - G - B", "Minor", R.drawable.e_minor, R.raw.e_minor),
        Chord("F Minor", "F - G# - C", "Minor", R.drawable.f_minor, R.raw.f_minor),
        Chord("G Minor", "G - A# - D", "Minor", R.drawable.g_minor, R.raw.g_minor),
        Chord("A Minor", "A - C - E", "Minor", R.drawable.a_minor, R.raw.a_minor),
        Chord("B Minor", "B - D - F#", "Minor", R.drawable.b_minor, R.raw.b_minor)
    )

    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tvTitle = findViewById(R.id.tvTitle)
        tvNotes = findViewById(R.id.tvNotes)
        tvType = findViewById(R.id.tvType)
        imgChord = findViewById(R.id.imgChord)
        btnBack = findViewById(R.id.btnBack)
        btnPrev = findViewById(R.id.btnPrev)
        btnNext = findViewById(R.id.btnNext)
        btnPlay = findViewById(R.id.btnPlay)

        index = intent.getIntExtra("INDEX", 0)

        tampilChord()

        btnBack.setOnClickListener { finish() }

        btnPlay.setOnClickListener {
            try {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer.create(this, chordList[index].audio)
                mediaPlayer?.start()
            } catch (e: Exception) {
                Toast.makeText(this, "Failed to play audio", Toast.LENGTH_SHORT).show()
            }
        }

        btnNext.setOnClickListener {
            if (index < chordList.lastIndex) {
                mediaPlayer?.release()
                mediaPlayer = null
                index++
                tampilChord()
            }
        }

        btnPrev.setOnClickListener {
            if (index > 0) {
                mediaPlayer?.release()
                mediaPlayer = null
                index--
                tampilChord()
            }
        }
    }

    private fun tampilChord() {
        val chord = chordList[index]
        tvTitle.text = chord.nama
        tvNotes.text = chord.notes
        tvType.text = chord.type
        imgChord.setImageResource(chord.image)

        if (chord.type.lowercase() == "major") {
            tvType.setBackgroundResource(R.drawable.tag_bg_major)
            tvType.setTextColor(ContextCompat.getColor(this, R.color.major_text))
        } else {
            tvType.setBackgroundResource(R.drawable.tag_bg_minor)
            tvType.setTextColor(ContextCompat.getColor(this, R.color.minor_text))
        }
    }

    override fun onDestroy() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }
}