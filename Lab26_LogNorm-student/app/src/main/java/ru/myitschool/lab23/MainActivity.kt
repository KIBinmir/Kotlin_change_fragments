package ru.myitschool.lab23

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.myitschool.lab23.databinding.ActivityMainBinding
import java.util.Random
import kotlin.math.exp

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private lateinit var ContentLayout: ConstraintLayout
    lateinit var generatedNumber: GeneratedNumber
    var num1: Double = 0.0
    var num2: Double = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = layoutInflater
        binding = ActivityMainBinding.inflate(inflater)
        setContentView(R.layout.activity_main)

        val provider = ViewModelProvider(this)
        generatedNumber = provider.get(GeneratedNumber::class.java)

        val textViewResult = findViewById<TextView>(R.id.random_number_result)
        val editViewMean = findViewById<EditText>(R.id.mean_value)
        val editViewVariance =findViewById<EditText>(R.id.variance_value)
        val buttonRandomNumber = findViewById<Button>(R.id.get_random_num)

        buttonRandomNumber.setOnClickListener {
            if (editViewMean.text.isNotEmpty()) {
                num1 = editViewMean.text.toString().toDouble()
                Log.d("EDMean", num1.toString())
            }

            if (editViewVariance.text.isNotEmpty()) {
                num2 = editViewMean.text.toString().toDouble()
                Log.d("EDVariance", num1.toString())
            }

            val normVal = exp(num2*Random().nextGaussian()+num1)

            Log.d("ANSWER", normVal.toString())
            textViewResult.text = normVal.toString()
            generatedNumber.saveData(normVal)

        }

        generatedNumber.answer.observe(this, Observer {
            textViewResult.text = it.toString()
        })
        /*val provider = ViewModelProvider(this)
        generatedNumber = provider.get(GeneratedNumber::class.java)

        observeViewModel()

        initView()*/
    }

    /*fun observeViewModel() {
        generatedNumber.MeanContainer().observe(this) {
            editViewMean.setText(it.toString())
        }
        generatedNumber.VarianceContainer().observe(this) {
            editViewVariance.setText(it.toString())
        }
        generatedNumber.ResultContainer().observe(this) {
            textViewResult.text=it.toString()
        }
    }

    fun initView() {
        buttonRandomNumber.setOnClickListener {
            generatedNumber.normVal()
        }
    }*/
}