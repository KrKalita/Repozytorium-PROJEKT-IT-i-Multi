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
class MainActivity2 : AppCompatActivity() {
//inicjalizacja komponentow z xmla i z firebase i inne
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

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
                Log.d("API","Api call succesful")
                JSON=it.toString();
                try {
                    var index1 = JSON.indexOf("label") + 8
                    nazwaPotrawy=liczenie(index1,JSON);
                }
                catch(e:Exception){
                    Log.e("cos","nie dziala")
                }


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