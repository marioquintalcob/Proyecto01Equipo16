package org.bedu.proyecto01equipo16

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
                R.id.comunidad -> replaceFragment(Comunity())
                R.id.nutricion -> replaceFragment(Nutrition())
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