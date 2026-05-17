package com.example.katalogchordpiano

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var etSearch: EditText
    lateinit var btnCari: Button
    lateinit var btnCMajor: Button
    lateinit var btnCMinor: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSearch = findViewById(R.id.etSearch)
        btnCari = findViewById(R.id.btnCari)
        btnCMajor = findViewById(R.id.btnCMajor)
        btnCMinor = findViewById(R.id.btnCMinor)

        btnCMajor.setOnClickListener {
            bukaDetail(
                "C Major",
                "C - E - G",
                "Major",
                R.drawable.c_major
            )
        }

        btnCMinor.setOnClickListener {
            bukaDetail(
                "C Minor",
                "C - D# - G",
                "Minor",
                R.drawable.c_minor
            )
        }

        btnCari.setOnClickListener {

            val input = etSearch.text.toString()

            if (input.isEmpty()) {
                Toast.makeText(
                    this,
                    "Input tidak boleh kosong",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (
                input.equals("C Major", true)
            ) {

                bukaDetail(
                    "C Major",
                    "C - E - G",
                    "Major",
                    R.drawable.c_major
                )

            } else if (
                input.equals("C Minor", true)
            ) {

                bukaDetail(
                    "C Minor",
                    "C - D# - G",
                    "Minor",
                    R.drawable.c_minor
                )

            } else {

                Toast.makeText(
                    this,
                    "Chord tidak ditemukan",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun bukaDetail(
        nama: String,
        notes: String,
        type: String,
        image: Int
    ) {

        val intent = Intent(this, DetailActivity::class.java)

        intent.putExtra("NAMA", nama)
        intent.putExtra("NOTES", notes)
        intent.putExtra("TYPE", type)
        intent.putExtra("IMAGE", image)

        startActivity(intent)
    }
}