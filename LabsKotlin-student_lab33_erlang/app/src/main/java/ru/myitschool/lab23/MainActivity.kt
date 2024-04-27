package ru.myitschool.lab23

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import ru.myitschool.lab23.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val but = findViewById<Button>(R.id.get_random_nums)
        but.setOnClickListener {
            val editTextRate = findViewById<EditText>(R.id.rate_param)
            val editTextSize = findViewById<EditText>(R.id.size_param)
            val editTextShape = findViewById<EditText>(R.id.shape_param)
            val intent = Intent(this, RecyclerActivity::class.java)
            val gam = editTextRate.text.toString()
            val n = editTextSize.text.toString()
            val k = editTextShape.text.toString()
            intent.putExtra("size", n)
            intent.putExtra("shape", k)
            intent.putExtra("rate", gam)
            startActivity(intent)
        }
    }
}