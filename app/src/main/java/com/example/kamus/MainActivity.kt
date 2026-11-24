package com.example.kamus

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainView = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        bottomNav.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.menu_tambah -> TambahFragment()
                R.id.menu_cari -> CariFragment()
                R.id.menu_daftar -> DaftarFragment()
                else -> return@setOnItemSelectedListener false
            }
            pasangFragment(fragment)
            true
        }

        // Saat pertama dibuka, pilih menu Tambah.
        // Hanya saat savedInstanceState == null, agar tidak menimpa fragment yang sudah ada
        if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.menu_tambah
        }
    }

    private fun pasangFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
