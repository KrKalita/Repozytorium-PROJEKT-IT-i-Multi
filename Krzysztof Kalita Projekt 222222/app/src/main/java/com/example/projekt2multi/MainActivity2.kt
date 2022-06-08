package com.example.projekt2multi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*
import kotlin.collections.ArrayList
//zmienne globalne
var JSON=""
var nazwaPotrawy=""
var wpiszNazwePotrawy="chicken"
var ileWykonac=0
var MoznaIscDalej=0
class MainActivity2 : AppCompatActivity() {
//inicjalizacja komponentow z xmla i z firebase i inne
    private val auth = FirebaseAuth.getInstance()
//    private lateinit var dodaj2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //inicjalizacja komponentow z xmla
//        dodaj2=findViewById(R.id.dodaj2)

    }
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        //funkcja pobierająca jsona ze strony internetowej i aktualizująca dane o potrawie
        if(ileWykonac>0){
            apiCall();
            ileWykonac--
        }
        return super.onCreateView(name, context, attrs)
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
    private fun apiCall(){
        //url strona na ktorej znajduje sie JSONA
        var url="https://api.edamam.com/api/recipes/v2?type=public&q="+wpiszNazwePotrawy+"&app_id=bd93760b&app_key=6aa520ecb0a861e014aca99309b1a2e8"
        //wykorzystanie biblioteki volley
        val queue= Volley.newRequestQueue(this)

        val jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET,url,null,
            {
                Log.d("wtf","Api call succesful")
                JSON=it.toString();
                var JSON2=it.toString();
                var index1 = JSON.indexOf("label")+8


//                var index2 = JSON.indexOf("icon")+7
//                var index3 = JSON.indexOf("sunrise")+9
//                var index4 = JSON.indexOf("sunset")+8
//                var index5 = JSON.indexOf("temp")+6
//                var index6 = JSON.indexOf("pressure")+10
//                var index7 = JSON.indexOf("timezone")+10
//                var index8 = JSON.indexOf("name")+7
                nazwaPotrawy=liczenie(index1,JSON);

                //WYCIAGNAC INNE PARAMTERY!!!!

                var description2=liczenie(index1,JSON);
//                icon=liczenie(index2,JSON);
//                sunrise=liczenie(index3,JSON);
//                sunset=liczenie(index4,JSON);
//                temp=liczenie(index5,JSON);
//                pressure=liczenie(index6,JSON);
//                timezone=liczenie(index7,JSON);
//                name=liczenie(index8,JSON);
            }, {
                Log.d("TAG","Api call failes")
            }
        )
        queue.add(jsonObjectRequest)
    }








    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logOut -> {
                auth.signOut()
                finish()
            }
        }
        return false
    }


}