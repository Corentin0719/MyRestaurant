package fr.isen.chieze.myrestaurant


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.chieze.myrestaurant.databinding.ActivityMenuBinding
import org.json.JSONObject


class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val title = intent.getStringExtra(HomeActivity.CATEGORY_KEY) ?: ""
        binding.categoryTitle.text = title
        binding.ListCategory.layoutManager = LinearLayoutManager(this)
        binding.ListCategory.adapter = CategoryAdapter(arrayListOf(),{})

        fromServer(title)

        val cartBtn = findViewById(R.id.cartBtn) as ImageView
        // set on-click listener
        cartBtn.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fromServer(category : String) {
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val obj = JSONObject()
        obj.put("id_shop", "1")
        val queue = Volley.newRequestQueue(this@MenuActivity)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, obj,
            { response ->

                Log.d("Response", "$response")
                Log.d("MenuActivity", "Api call succes")
                val menu = Gson().fromJson(response.toString(), Data::class.java)

                val items = menu.data.firstOrNull{ it.name_fr == category }?.items ?: arrayListOf() // "?."  propage le null et "?:" si c'est null, Si il n'a pas trouv?? d'??lement par rapport ?? la cat??gorie,il renvoie null
                val adapter = CategoryAdapter(items) {
                    val intent = Intent(this@MenuActivity, DetailsActivity::class.java)

                    intent.putExtra(DETAILS_KEY, it)
                    startActivity(intent)
                }
                binding.loaderIcon.visibility = View.GONE
                binding.ListCategory.adapter = adapter

            },
            { error ->
                Log.d("MenuActivity", "La requ??te ?? ??chou??")
            }
        )
        queue.add(jsonObjectRequest)
    }

    companion object {
        const val TITLE_KEY = "title"
        const val DETAILS_KEY = "details"
    }




}


