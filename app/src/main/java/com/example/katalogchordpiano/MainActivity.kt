package com.example.katalogchordpiano

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "42430013"
    }

    private lateinit var etSearch: EditText
    private lateinit var btnCari: ImageButton
    private lateinit var btnAZ: android.widget.Button
    private lateinit var btnZA: android.widget.Button
    private lateinit var listChord: ListView
    private lateinit var adapter: ChordAdapter

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.activity_main)
            Log.d(TAG, "MainActivity: onCreate - Aplikasi dimulai")

            etSearch = findViewById(R.id.etSearch)
            btnCari = findViewById(R.id.btnCari)
            btnAZ = findViewById(R.id.btnAZ)
            btnZA = findViewById(R.id.btnZA)
            listChord = findViewById(R.id.listChord)

            setupListView()

            listChord.setOnItemClickListener { _, _, position, _ ->
                Log.i(TAG, "MainActivity: Item diklik pada posisi $position")
                bukaDetail(position)
            }

            btnCari.setOnClickListener {
                try {
                    val input = etSearch.text.toString().trim()
                    Log.d(TAG, "MainActivity: Mencari chord '$input'")
                    if (input.isEmpty()) {
                        Toast.makeText(this, "Please enter a chord name", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    val index = chordList.indexOfFirst { it.nama.equals(input, true) }
                    if (index != -1) {
                        Log.i(TAG, "MainActivity: Chord ditemukan: ${chordList[index].nama}")
                        bukaDetail(index)
                    } else {
                        Log.w(TAG, "MainActivity: Chord '$input' tidak ditemukan")
                        Toast.makeText(this, "Chord not found", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "MainActivity: Kesalahan saat pencarian - ${e.message}")
                }
            }

            btnAZ.setOnClickListener {
                try {
                    Log.d(TAG, "MainActivity: Mengurutkan A-Z")
                    chordList.sortBy { it.nama }
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "Sorted A-Z", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Log.e(TAG, "MainActivity: Gagal sorting A-Z - ${e.message}")
                }
            }

            btnZA.setOnClickListener {
                try {
                    Log.d(TAG, "MainActivity: Mengurutkan Z-A")
                    chordList.sortByDescending { it.nama }
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "Sorted Z-A", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Log.e(TAG, "MainActivity: Gagal sorting Z-A - ${e.message}")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "MainActivity: Fatal Error di onCreate - ${e.message}")
            Toast.makeText(this, "Terjadi kesalahan saat memuat aplikasi", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupListView() {
        try {
            adapter = ChordAdapter(this, chordList)
            listChord.adapter = adapter
            Log.d(TAG, "MainActivity: ListView berhasil dikonfigurasi")
        } catch (e: Exception) {
            Log.e(TAG, "MainActivity: Error setup ListView - ${e.message}")
        }
    }

    private fun bukaDetail(index: Int) {
        try {
            Log.i(TAG, "MainActivity: Berpindah ke DetailActivity - Indeks: $index")
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("INDEX", index)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "MainActivity: Gagal membuka halaman detail - ${e.message}")
            Toast.makeText(this, "Gagal memuat detail chord", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.v(TAG, "MainActivity: onStart - Aplikasi berada di foreground")
    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG, "MainActivity: onResume - Aplikasi siap interaksi")
    }

    override fun onPause() {
        super.onPause()
        Log.v(TAG, "MainActivity: onPause - Aktivitas dijeda")
    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, "MainActivity: onStop - Aktivitas berhenti di latar belakang")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "MainActivity: onDestroy - Aktivitas dihancurkan")
    }
}
