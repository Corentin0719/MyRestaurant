package fr.isen.chieze.myrestaurant


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.chieze.myrestaurant.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = intent

        val item = intent.getSerializableExtra(MenuActivity.DETAILS_KEY) as Items
        binding.foodTitle.text = item.name_fr



        binding.detailsFood.text = item.ingredients.joinToString(", ", transform = { it.name_fr })


    }
}