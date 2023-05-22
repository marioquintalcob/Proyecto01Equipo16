package org.bedu.proyecto01equipo16.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.bedu.proyecto01equipo16.MainActivity
import org.bedu.proyecto01equipo16.databinding.FragmentProfileBinding
import org.bedu.proyecto01equipo16.login.LoginActivity

class Profile : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.textViewLogout.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
        }
    }
}