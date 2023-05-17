package org.bedu.proyecto01equipo16

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var adapter: CustomAdapter
private lateinit var recyclerView: RecyclerView
private lateinit var titlesArraylist: ArrayList<workouts>

lateinit var imageId: Array<Int>
lateinit var titles: Array<String>
lateinit var details: Array<String>

/**
 * A simple [Fragment] subclass.
 * Use the [Nutrition.newInstance] factory method to
 * create an instance of this fragment.
 */
class Nutrition : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nutrition, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Nutrition.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Nutrition().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView=view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager=layoutManager
        recyclerView.setHasFixedSize(true)
        adapter= CustomAdapter(titlesArraylist)
        recyclerView.adapter = adapter
    }

    private fun dataInitialize(){
       titlesArraylist = arrayListOf<workouts>()
        val imageId = intArrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,)

        titles = arrayOf(
            getString(R.string.titles_a),
            getString(R.string.titles_b),
            getString(R.string.titles_c),
            getString(R.string.titles_d)

        )
        details = arrayOf(
            getString(R.string.details_a),
            getString(R.string.details_b),
            getString(R.string.details_c),
            getString(R.string.details_d)

        )

        for(i in imageId.indices){
            val details = workouts(titles[i], details[i])
            titlesArraylist.add(details)
        }
    }
}