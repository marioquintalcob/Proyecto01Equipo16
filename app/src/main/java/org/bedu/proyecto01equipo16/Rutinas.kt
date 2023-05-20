package org.bedu.proyecto01equipo16

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



private lateinit var adapter: CustomAdapter
private lateinit var recyclerView: RecyclerView
private lateinit var titlesArraylist: ArrayList<workouts>

lateinit var imagenejercicio: Array<Int>
lateinit var nombreejercicio: Array<String>
lateinit var details: Array<String>

class Rutinas : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nutrition, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        datosRutinas()
        val layoutManager = LinearLayoutManager(context)
        recyclerView=view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager=layoutManager
        recyclerView.setHasFixedSize(true)
        adapter= CustomAdapter(titlesArraylist)
        recyclerView.adapter = adapter
    }

    private fun datosRutinas(){
       titlesArraylist = arrayListOf<workouts>()
        imagenejercicio = arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,)


        details = arrayOf(
            getString(R.string.titles_a),
            getString(R.string.titles_b),
            getString(R.string.titles_c),
            getString(R.string.titles_d),
            getString(R.string.titles_a),
            getString(R.string.titles_b),
            getString(R.string.titles_c),
            getString(R.string.titles_d),

        )
        nombreejercicio = arrayOf(
            getString(R.string.details_a),
            getString(R.string.details_b),
            getString(R.string.details_c),
            getString(R.string.details_d),
                    getString(R.string.details_a),
            getString(R.string.details_b),
            getString(R.string.details_c),
            getString(R.string.details_d),
        )

        for(i in imagenejercicio.indices){
            val details = workouts(nombreejercicio[i], details[i])
            titlesArraylist.add(details)
        }
    }
}