package com.example.projekt2multi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentWpisz1.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentWpisz1 : Fragment() {
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
        return inflater.inflate(R.layout.fragment_wpisz1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.dalej1).apply {
            setOnClickListener {
                wpiszNazwePotrawy=view.findViewById<EditText>(R.id.wpiszPotrawe).text.toString();
                ileWykonac=1//wykonanie apiCall() w mainactivity2
                view.findNavController().navigate(R.id.fragmentPrzejscie2)
            }
        }
//        view.findViewById<Button>(R.id.powrot1).apply {
//            setOnClickListener {
//                wpiszMiejscowosc=view.findViewById<EditText>(R.id.wpiszMiejscowosc1).text.toString();
//                view.findNavController().navigate(R.id.fragmentMenu)
//            }
//        }
//        view.findViewById<Button>(R.id.seniorzy2).apply {
//            setOnClickListener {
//                wpiszMiejscowosc=view.findViewById<EditText>(R.id.wpiszMiejscowosc1).text.toString();
//                view.findNavController().navigate(R.id.fragmentSeniorzy1)
//            }
//        }
//                view.findViewById<EditText>(R.id.wpiszMiejscowosc1).setText(wpiszMiejscowosc)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentWpisz1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentWpisz1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}