package org.bedu.proyecto01equipo16

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import org.bedu.proyecto01equipo16.databinding.FragmentHomeBinding

class Home : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            for (viewE in group.children) {
                val radio = viewE as RadioButton
                radio.setBackgroundColor(ContextCompat.getColor(view.context, R.color.white))
                radio.setTextColor(ContextCompat.getColor(view.context, R.color.black))
            }

            val radioButton = view.findViewById<RadioButton>(checkedId)
            radioButton.background = ContextCompat.getDrawable(view.context, R.drawable.orange_circle_drawable)
            radioButton.setTextColor(ContextCompat.getColor(view.context, R.color.white))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}