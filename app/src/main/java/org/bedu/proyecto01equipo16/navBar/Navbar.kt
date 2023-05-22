package org.bedu.proyecto01equipo16.navBar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.bedu.proyecto01equipo16.news.News
import org.bedu.proyecto01equipo16.home.Home
import org.bedu.proyecto01equipo16.profile.Profile
import org.bedu.proyecto01equipo16.R
import org.bedu.proyecto01equipo16.routines.Rutinas
import org.bedu.proyecto01equipo16.databinding.ActivityNavbarBinding

class Navbar : AppCompatActivity() {

    private lateinit var binding: ActivityNavbarBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityNavbarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId){

                R.id.home -> replaceFragment(Home())
                R.id.comunidad -> replaceFragment(News())
                R.id.rutinas -> replaceFragment(Rutinas())
                R.id.perfil -> replaceFragment(Profile())

                else -> {


                }

            }
            true

        }

    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()

    }

}