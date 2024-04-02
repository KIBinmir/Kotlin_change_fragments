package ru.myitschool.mte

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import ru.myitschool.mte.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var btnStart: Button? = null
    private var btnStop: Button? = null
    private var changeFlag: Boolean = false
    private lateinit var transaction: FragmentTransaction
    private lateinit var firstFragment: FirstFragment
    private lateinit var proceedingFragment: ProceedingFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        btnStart = binding!!.content.startBtn
        btnStop = binding!!.content.stopBtn

        firstFragment = FirstFragment()
        proceedingFragment = ProceedingFragment()

        transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding!!.content.outputFragment.id, firstFragment).commit()

        btnStart!!.setOnClickListener( {
            changeFlag = true
            Toast.makeText(this, "Start change fragments", Toast.LENGTH_SHORT).show()

            })
        btnStop!!.setOnClickListener( {
            changeFlag = false
            Toast.makeText(this, "Stop change fragments", Toast.LENGTH_SHORT).show()})

        Thread(Runnable {
            while(changeFlag) {
                runOnUiThread{ transaction.replace(firstFragment.id, proceedingFragment).commit() }
                // останавливаем поток на 3 секунды
                Thread.sleep(3000)

                // замена фрагмента
                runOnUiThread{ transaction.replace(proceedingFragment.id, firstFragment).commit() }

                // останавливаем поток на 3 секунды
                Thread.sleep(3000)
            }
        })
    }
}