package com.example.katalogchordpiano

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    lateinit var tvTitle: TextView
    lateinit var tvNotes: TextView
    lateinit var tvType: TextView
    lateinit var imgChord: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tvTitle = findViewById(R.id.tvTitle)
        tvNotes = findViewById(R.id.tvNotes)
        tvType = findViewById(R.id.tvType)
        imgChord = findViewById(R.id.imgChord)

        val nama = intent.getStringExtra("NAMA")
        val notes = intent.getStringExtra("NOTES")
        val type = intent.getStringExtra("TYPE")
        val image = intent.getIntExtra("IMAGE", 0)

        tvTitle.text = nama
        tvNotes.text = "Notes : $notes"
        tvType.text = "Type : $type"

        imgChord.setImageResource(image)
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}