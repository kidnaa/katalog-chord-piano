package com.example.katalogchordpiano

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etSearch: EditText
    private lateinit var btnCari: Button
    private lateinit var btnAZ: Button
    private lateinit var btnZA: Button
    private lateinit var listChord: ListView

    private val chordList = arrayListOf(

        // MAJOR
        Chord("C Major", "C - E - G", "Major", R.drawable.c_major),
        Chord("D Major", "D - F# - A", "Major", R.drawable.d_major),
        Chord("E Major", "E - G# - B", "Major", R.drawable.e_major),
        Chord("F Major", "F - A - C", "Major", R.drawable.f_major),
        Chord("G Major", "G - B - D", "Major", R.drawable.g_major),
        Chord("A Major", "A - C# - E", "Major", R.drawable.a_major),
        Chord("B Major", "B - D# - F#", "Major", R.drawable.b_major),

        // MINOR
        Chord("C Minor", "C - D# - G", "Minor", R.drawable.c_minor),
        Chord("D Minor", "D - F - A", "Minor", R.drawable.d_minor),
        Chord("E Minor", "E - G - B", "Minor", R.drawable.e_minor),
        Chord("F Minor", "F - G# - C", "Minor", R.drawable.f_minor),
        Chord("G Minor", "G - A# - D", "Minor", R.drawable.g_minor),
        Chord("A Minor", "A - C - E", "Minor", R.drawable.a_minor),
        Chord("B Minor", "B - D - F#", "Minor", R.drawable.b_minor)
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSearch = findViewById(R.id.etSearch)
        btnCari = findViewById(R.id.btnCari)
        btnAZ = findViewById(R.id.btnAZ)
        btnZA = findViewById(R.id.btnZA)
        listChord = findViewById(R.id.listChord)

        tampilkanData()

        listChord.setOnItemClickListener { _, _, position, _ ->
            bukaDetail(position)
        }

        btnCari.setOnClickListener {

            val input =
                etSearch.text
                    .toString()
                    .trim()

            if (input.isEmpty()) {

                Toast.makeText(
                    this,
                    "Input tidak boleh kosong",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            var index = -1

            for (i in chordList.indices) {

                if (
                    chordList[i]
                        .nama
                        .equals(input, true)
                ) {

                    index = i
                    break

                }

            }

            if (index != -1) {

                bukaDetail(index)

            } else {

                Toast.makeText(
                    this,
                    "Chord tidak ditemukan",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }

        btnAZ.setOnClickListener {

            bubbleSort(true)

            tampilkanData()

            Toast.makeText(
                this,
                "Data diurutkan A-Z",
                Toast.LENGTH_SHORT
            ).show()

        }

        btnZA.setOnClickListener {

            bubbleSort(false)

            tampilkanData()

            Toast.makeText(
                this,
                "Data diurutkan Z-A",
                Toast.LENGTH_SHORT
            ).show()

        }

    }

    private fun tampilkanData() {

        val data =
            chordList.map {
                it.nama
            }

        val adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                data
            )

        listChord.adapter =
            adapter

    }

    private fun bubbleSort(
        ascending: Boolean
    ) {

        for (i in 0 until chordList.size) {

            for (j in 0 until chordList.size - i - 1) {

                val swap =

                    if (ascending)

                        chordList[j].nama >
                                chordList[j + 1].nama

                    else

                        chordList[j].nama <
                                chordList[j + 1].nama


                if (swap) {

                    val temp =
                        chordList[j]

                    chordList[j] =
                        chordList[j + 1]

                    chordList[j + 1] =
                        temp

                }

            }

        }

    }

    private fun bukaDetail(
        index: Int
    ) {

        val intent =
            Intent(
                this,
                DetailActivity::class.java
            )

        intent.putExtra(
            "INDEX",
            index
        )

        startActivity(intent)

    }

}