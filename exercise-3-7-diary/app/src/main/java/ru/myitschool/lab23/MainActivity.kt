package ru.myitschool.lab23

import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ru.myitschool.lab23.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var btnSave: Button
    private lateinit var titleEditText: EditText
    private lateinit var noteEditText: EditText
    private lateinit var timeEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnSave = findViewById<Button>(R.id.save)
        titleEditText = findViewById<EditText>(R.id.event_title_user_input)
        noteEditText = findViewById<EditText>(R.id.event_notes_user_input)
        timeEditText = findViewById<EditText>(R.id.event_time_user_input)
        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        val sfm = supportFragmentManager
        btnSave.setOnClickListener {
            if (titleEditText.text.toString() == "") {
                Snackbar.make(
                    findViewById(R.id.content),
                    "Введите название события!",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                val dialogData = DialogData()
                dialogData.title = titleEditText.text.toString()
                dialogData.note = noteEditText.text.toString()
                dialogData.date = calendarView.date.toString()
                dialogData.time = timeEditText.text.toString()
                dialogData.show(sfm, "DialogSaveData")
                timeEditText.text.clear()
                noteEditText.text.clear()
                titleEditText.text.clear()
            }
        }
    }
}
