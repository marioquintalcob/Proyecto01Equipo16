package org.bedu.proyecto01equipo16

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.bedu.proyecto01equipo16.comunity.ComunityAdapter
import org.bedu.proyecto01equipo16.comunity.ComunityProvider

class Comunity : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comunity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Muestra el contenido del Recyclerview en el fragment Comunity
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerComunity)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ComunityAdapter(ComunityProvider.articulosList)
    }

}