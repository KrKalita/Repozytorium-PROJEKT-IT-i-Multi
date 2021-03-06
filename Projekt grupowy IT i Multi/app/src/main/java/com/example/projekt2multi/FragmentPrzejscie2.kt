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
 * Use the [FragmentPrzejscie2.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPrzejscie2 : Fragment() {
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
        return inflater.inflate(R.layout.fragment_przejscie2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //jak klikne tak
        view.findViewById<Button>(R.id.tak).apply {
            setOnClickListener {
                //trzeba poczekac chwilę żeby apiCall() się wykonało w mainactivity2
                if(JSON==""){
                    Toast.makeText(getActivity(), "Poczekaj chwilę!!!", Toast.LENGTH_LONG).show();
                }else if(JSON.length<50){
                    Toast.makeText(getActivity(), "Nie znaleziono przepisu!\nSprobuj jeszcze raz.", Toast.LENGTH_LONG).show();
                    JSON=""
                    view.findNavController().navigate(R.id.fragmentWpisz1)

                }
                else
                {
                    view.findNavController().navigate(R.id.fragmentDane3)
                }
            }
        }
        //jak klikne nie
        view.findViewById<Button>(R.id.nie).apply {
            setOnClickListener {
                JSON=""
                view.findNavController().navigate(R.id.fragmentWpisz1)
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentPrzejscie2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentPrzejscie2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}