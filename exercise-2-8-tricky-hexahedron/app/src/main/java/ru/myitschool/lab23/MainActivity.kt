package ru.myitschool.lab23

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
//import android.view.View
//import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal
import java.math.RoundingMode
//import kotlin.math.round
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    //private val maxValue: Double = 10e12
    //private var choose: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val option = resources.getStringArray(R.array.actions)
        val spin = findViewById<Spinner>(R.id.spinner)

        val but = findViewById<Button>(R.id.calculate)
        val a = findViewById<EditText>(R.id.side_a)
        val b = findViewById<EditText>(R.id.side_b)
        val c = findViewById<EditText>(R.id.side_c)

        val ta = findViewById<TextView>(R.id.side_a_label)
        val txt = findViewById<TextView>(R.id.solution)


        val adapt = ArrayAdapter(this,android.R.layout.simple_spinner_item, option)
        spin.adapter = adapt

        but.setOnClickListener {
            val numA = BigDecimal(a.text.toString())
            val numB = BigDecimal(b.text.toString())
            val numC = BigDecimal(c.text.toString())
            val mathRes = when(spin.selectedItem.toString()) {
                "Сумма длин сторон" -> numA.add(numB).add(numC).multiply(BigDecimal(4))
                "Длина диагонали" -> sqrt((numA.pow(2) + numB.pow(2) + numC.pow(2)).toDouble()).toBigDecimal()
                "Площадь полной поверхности" -> numA.multiply(numB).add(numB.multiply(numC)).add(numA.multiply(numC)).multiply(BigDecimal(2))
                "Объем" -> numA.multiply(numB).multiply(numC)
                else -> BigDecimal.ZERO
            }
            //txt.text = String.format("%.20f", mathRes)
            txt.text = mathRes.setScale(20, RoundingMode.HALF_UP).toString()

        }

        txt.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Solution", txt.text)
            clipboard.setPrimaryClip(clip)
        }
    }

    /*private fun sum(a: Double, b: Double, c: Double): Double = 4 * (a + b + c)
    private fun diag(a: Double, b: Double, c: Double): Double = sqrt(a*a + b*b + c*c)
    private fun square(a: Double, b: Double, c: Double): Double = 2*(a*b + b*c + a*c)
    private fun volume(a: Double, b: Double, c: Double): Double = a*b*c*/

    /*private fun sum(a: BigDecimal, b: BigDecimal, c: BigDecimal): BigDecimal = a.add(b).add(c).multiply(BigDecimal(4))
    private fun diag(a: BigDecimal, b: BigDecimal, c: BigDecimal): BigDecimal = sqrt((a.pow(2) + b.pow(2) + c.pow(2)).toDouble()).toBigDecimal()
    private fun square(a: BigDecimal, b: BigDecimal, c: BigDecimal): BigDecimal = a.multiply(b).add(b.multiply(c)).add(c.multiply(a)).multiply(BigDecimal(2))
    private fun volume(a: BigDecimal, b: BigDecimal, c: BigDecimal): BigDecimal = a.multiply(b).multiply(c)*/
}