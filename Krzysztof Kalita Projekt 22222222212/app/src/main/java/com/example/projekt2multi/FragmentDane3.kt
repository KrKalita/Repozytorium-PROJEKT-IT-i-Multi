package com.example.projekt2multi

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.*
import android.text.format.Time.getCurrentTimezone
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController

import android.util.DisplayMetrics
import android.util.TypedValue
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_dane3.view.*
import java.util.concurrent.Executors


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentDane3.newInstance] factory method to
 * create an instance of this fragment.
 */
//zmienne globalne
var godzinaWJakimsKraju=""
var dataWJakimsKraju=""
var zachodSlonca=""
var wschodSlonca=""
var name2=""
var czyZaczynasz=0
class FragmentDane3 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var Potrawa_zdj:ImageView
    private lateinit var Potrawa_zdj_URL:String

    private lateinit var tluszcze:TextView
    private lateinit var weglowodany:TextView
    private lateinit var bialka:TextView
    private lateinit var sol:TextView
    private lateinit var kalorie:TextView
    private lateinit var link:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dane3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Potrawa_zdj=view.findViewById(R.id.Potrawa_zdj)
        tluszcze=view.findViewById(R.id.tluszcze_text)
        weglowodany=view.findViewById(R.id.weglowodany_txt)
        bialka=view.findViewById(R.id.bialko_txt)
        sol=view.findViewById(R.id.sol_txt)
        kalorie=view.findViewById(R.id.kalorie_txt)
        link=view.findViewById(R.id.link)



//        if(name==name2){
//            Toast.makeText(activity,"Ta sama miejscowość!!!\nMożliwe powody:\n1. Źle wpisana miejscowść.\n2. Brak danych o danej miejscowości.\n3. Wpisana ta sama miejscowość.", Toast.LENGTH_LONG).show();
//        }
        var index1 = JSON.indexOf("label")+8
        var index3 = JSON.indexOf("image")+8
        var index4 = JSON.indexOf("totalNutrients")+59
        var index5 = JSON.indexOf("fatContent")+20
        var index6 = JSON.indexOf("carbohydrateContent")+29
        var index7 = JSON.indexOf("proteinContent")+24
        var index8 = JSON.indexOf("sodiumContent")+23
        var index9 = JSON.indexOf("source")+12
//zabezpieczenie
            if(JSON==""){
                nazwaPotrawy="blad"
            }else{
                nazwaPotrawy=liczenie(index1,JSON)
                try {
                    Potrawa_zdj_URL = (liczenieURL(index3, JSON)).replace("\\", "")
                    Picasso.get().load(Potrawa_zdj_URL).into(Potrawa_zdj)
                    kalorie.text = liczeniewartosci(index4, JSON) + " kcal"
                    tluszcze.text = liczeniewartosci(index5, JSON) + " mg"
                    weglowodany.text = liczeniewartosci(index6, JSON) + " mg"
                    bialka.text = liczeniewartosci(index7, JSON) + " mg"
                    sol.text = liczeniewartosci(index8, JSON) + " mg"
                    link.text=liczenielinkURL(liczenielink(index9,JSON),JSON).replace("\\", "")
                    Linkify.addLinks(link,Linkify.WEB_URLS)
                }
                catch(e:Exception){
                    Log.e(TAG,e.toString())
                }
            }

        view.findViewById<TextView>(R.id.Potrawa).text=nazwaPotrawy;
        czyZaczynasz=1
//        name2=view.findViewById<TextView>(R.id.miejscowosc1).text.toString()
//        view.findViewById<TextView>(R.id.krotkiOpis1).text= "Krótki opis słowny: "+description;
//        //Zamiana Kelwiny na Celsius
//        var temperatura_celsius= round((temp.toDouble()-273.15)*100)/100
//        var temperatura_celsius2=temperatura_celsius.toString()+"°C"
//        view.findViewById<TextView>(R.id.temperatura1).text= temperatura_celsius2;
//        view.findViewById<TextView>(R.id.cisnienie1).text= pressure+" hPa";
//        view.findViewById<TextView>(R.id.godzinaWschodu1).text= wschodSlonca;
//        view.findViewById<TextView>(R.id.godzinaZachodu1).text= zachodSlonca;
//        view.findViewById<TextView>(R.id.czas1).text= godzinaWJakimsKraju;
//        view.findViewById<TextView>(R.id.data1).text= dataWJakimsKraju;
//
//        var obraz=view.findViewById<ImageView>(R.id.obraz1)
////        icon="03n"
//        //wybranie ikony dla pogody
//        if(icon=="01d"){
//                    obraz.setBackgroundResource(R.drawable.icon_01d)
//        }else if(icon=="02d"){
//            obraz.setBackgroundResource(R.drawable.icon_02d)
//        }else if(icon=="03d"){
//            obraz.setBackgroundResource(R.drawable.icon_03d)
//        }else if(icon=="04d"){
//            obraz.setBackgroundResource(R.drawable.icon_04d)
//        }else if(icon=="09d"){
//            obraz.setBackgroundResource(R.drawable.icon_09d)
//        }else if(icon=="10d"){
//            obraz.setBackgroundResource(R.drawable.icon_10d)
//        }else if(icon=="11d"){
//            obraz.setBackgroundResource(R.drawable.icon_11d)
//        }else if(icon=="13d"){
//            obraz.setBackgroundResource(R.drawable.icon_13d)
//        }else if(icon=="50d"){
//            obraz.setBackgroundResource(R.drawable.icon_50d)
//        }else if(icon=="01n"){
//            obraz.setBackgroundResource(R.drawable.icon_01n)
//        }else if(icon=="02n"){
//            obraz.setBackgroundResource(R.drawable.icon_02n)
//        }else if(icon=="03n"){
//            obraz.setBackgroundResource(R.drawable.icon_03n)
//        }else if(icon=="04n"){
//            obraz.setBackgroundResource(R.drawable.icon_04n)
//        }else if(icon=="09n"){
//            obraz.setBackgroundResource(R.drawable.icon_09n)
//        }else if(icon=="10n"){
//            obraz.setBackgroundResource(R.drawable.icon_10n)
//        }else if(icon=="11n"){
//            obraz.setBackgroundResource(R.drawable.icon_11n)
//        }else if(icon=="13n"){
//            obraz.setBackgroundResource(R.drawable.icon_13n)
//        }else if(icon=="50n"){
//            obraz.setBackgroundResource(R.drawable.icon_50n)
//        }else{
//            obraz.setBackgroundResource(R.drawable.icon_blad)
//        }

        view.findViewById<Button>(R.id.powrot).apply {
            setOnClickListener {
                JSON=""
                view.findNavController().navigate(R.id.fragmentWpisz1)
            }
        }
        view.findViewById<Button>(R.id.przejdzDoNastepnejPotrawy).apply {
            setOnClickListener {
                //usuwanie pierwszej potrawy ze stringa za każdym razem jak nacisniemy i aktualizacja danych
                //DODAC INNE PARAMETRY!!!!!!!!!!
                var index2 = JSON.indexOf("edamam.owl")+10
                if(index2==9){
                }else{
                    if(czyZaczynasz==1){
                        JSON=JSON.drop(index2)
                        index2 = JSON.indexOf("edamam.owl")+10
                        JSON=JSON.drop(index2)
                        czyZaczynasz=0
                    }else{
                        JSON=JSON.drop(index2)
                    }
                }
                var index1 = JSON.indexOf("label")+8
                var index3=JSON.indexOf("image")+8
                var index4 = JSON.indexOf("totalNutrients")+59
                var index5 = JSON.indexOf("fatContent")+20
                var index6 = JSON.indexOf("carbohydrateContent")+29
                var index7 = JSON.indexOf("proteinContent")+24
                var index8 = JSON.indexOf("sodiumContent")+23
                var index9 = JSON.indexOf("source")+9
                if(JSON==""){
                    nazwaPotrawy="blad"
                }else{
                    nazwaPotrawy=liczenie(index1,JSON);
                    Potrawa_zdj_URL=(liczenieURL(index3,JSON)).replace("\\", "")
                    Picasso.get().load(Potrawa_zdj_URL).into(view.findViewById<ImageView>(R.id.Potrawa_zdj))
                    try{
                    kalorie.text=liczeniewartosci(index4,JSON)+" kcal"
                    tluszcze.text=liczeniewartosci(index5,JSON)+" mg"
                    weglowodany.text=liczeniewartosci(index6,JSON)+" mg"
                    bialka.text=liczeniewartosci(index7,JSON)+" mg"
                    sol.text=liczeniewartosci(index7,JSON)+" mg"
//                    link.text=liczenielinkURL(liczenielink(index9,JSON),JSON).replace("\\", "")
//                    Linkify.addLinks(link,Linkify.WEB_URLS)
                    }
                    catch(e:Exception){
                        Log.e(TAG,e.toString())
                    }
                }
                view.findViewById<TextView>(R.id.Potrawa).text=nazwaPotrawy;

            }
        }

//        view.findViewById<Button>(R.id.seniorzy3).apply {
//            setOnClickListener {
//                view.findNavController().navigate(R.id.fragmentSeniorzy2)
//            }
//        }
    }
    //wyciągnięcie danych ze Stringa
    private fun liczenie(index:Int,json:String): String {
        var index1=index;
        var description="";
        for (i in 1..40) {
            if(json[index1]=='"'||json[index1]==','||json[index1]=='}'){
                break;
            }
            description=description+json[index1].toString();
            index1++;

        }
        return description;
    }

    private fun liczenieURL(index:Int,json:String): String {
        var index3=index;
        var description="";
        for (i in 1..2000) {
            if(json[index3]=='"'||json[index3]==','||json[index3]=='}'){
                break;
            }
            description=description+json[index3].toString();
            index3++;

        }
        return description;
    }
    private fun liczeniewartosci(index:Int,json:String): String {
        var index1=index;
        var description="";
        for (i in 1..40) {
            if(json[index1]=='"'||json[index1]==','||json[index1]=='}'||json[index1]=='.'){
                break;
            }
            description=description+json[index1].toString();
            index1++;

        }
        return description;
    }
    private fun liczenielink(index:Int,json:String): Int {
        var index1=index;
        var description="";
        for (i in 1..40) {
            if(json[index1]==':'){
                break;
            }
            index1++;

        }
        return index1+2
    }

    private fun liczenielinkURL(index:Int,json:String): String {
        var index3=index;
        var description="";
        for (i in 1..2000) {
            if(json[index3]=='"'||json[index3]==','||json[index3]=='}'){
                break;
            }
            description=description+json[index3].toString();
            index3++;

        }
        return description;
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentDane3.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentDane3().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}