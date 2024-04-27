package ru.myitschool.lab23

import android.app.AlertDialog
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogData: DialogFragment() {
    var title: String = ""
    var date: String = ""
    var time: String = ""
    var note: String = ""
    override fun onCreateDialog(savedInstanceState: Bundle?) =
        AlertDialog.Builder(context)
                .setMessage("Записано!\n" +
                        "Событие: ${title}\n" +
                "Дата: ${date}\n" +
                "Время: ${time}\n" +
                "Заметки: ${note}").setPositiveButton("OK", activity as OnClickListener).create()
}