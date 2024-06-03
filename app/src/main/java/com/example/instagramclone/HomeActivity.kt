package com.example.instagramclone

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.instagramclone.databinding.ActivityHomeBinding
import com.example.instagramclone.fragments.AddFragment
import com.example.instagramclone.fragments.ProfileFragment
import com.example.instagramclone.fragments.ReelFragment
import com.example.instagramclone.fragments.SearchFragment
import com.example.instagramclone.fragments.YourFragment
import com.google.android.gms.dynamic.SupportFragmentWrapper

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(YourFragment())

        binding.navView.setOnItemSelectedListener { item ->

            when (item.itemId) {

                R.id.home -> {
                    setFragment(YourFragment())

                }

                R.id.search -> {
                    setFragment(SearchFragment())
                }

                R.id.add -> {
                    setFragment(AddFragment())
                }

                R.id.reel -> {
                    setFragment(ReelFragment())
                }

                R.id.profile -> {
                    setFragment(ProfileFragment())
                }
            }
            true
        }
    }

    private fun setFragment(fragment: Fragment) {
        val layout = supportFragmentManager.beginTransaction()
        layout.replace(R.id.Framelayout, fragment)
        layout.commit()
    }
}