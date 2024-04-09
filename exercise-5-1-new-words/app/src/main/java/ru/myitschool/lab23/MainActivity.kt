package ru.myitschool.lab23

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException
import java.util.Calendar

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var writeExternal: Button
    private lateinit var writeInternal: Button
    private lateinit var tv_fileContent: TextView
    private lateinit var et_wordsInput: EditText
    private var filename: String = "words.txt"
    private var filepath: String = "4"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        writeExternal = findViewById(R.id.write_external) as Button
        writeInternal = findViewById(R.id.write_internal) as Button
        tv_fileContent = findViewById<TextView>(R.id.file_content)
        et_wordsInput = findViewById<EditText>(R.id.words_input)

        writeExternal.setOnClickListener(this)
        writeInternal.setOnClickListener(this)

    }
    fun printMessage(m: String?) {
        Toast.makeText(this, m, Toast.LENGTH_LONG).show()
    }

    override fun onClick(view: View) {
        val b: Button = view as Button
        val t: String = b.text.toString()
        when(t) {
            "External" -> {
                writeData("external")
                //readData("external")
            }
            "Internal" -> {
                writeData("internal")
                //readData("internal")
            }
        }
    }
    private fun writeData(storage: String) {
        val data = et_wordsInput.text.toString()

        val list_words = data.replace(" ", "").split(",")
        tv_fileContent.text = list_words.size.toString()
        val month = (Calendar.getInstance().get(Calendar.MONTH)+1).toString()
        et_wordsInput.setText("")
        val dir = when(storage) {
            "internal" -> {
                File(applicationContext.filesDir, month)
            }
            "external" -> {
                getExternalFilesDir(month)
            }
            else -> {
                File(month)
            }
        }
        if (!dir!!.exists()) {
            dir.mkdir()
        }
        val file = File(dir, "words.txt")
        try {
            file.createNewFile()
            val wr = BufferedWriter(FileWriter(file))
            list_words.forEach { wr.write("$it\n") }
            wr.close()
        }
        catch (_: IOException) {}
        /*when(storage) {
            "internal" -> {
                //val myInternalFile = File(getDir(filepath, Context.MODE_PRIVATE), filename)
                try {
                    //val fos: FileOutputStream = FileOutputStream(myInternalFile)
                    val fos: FileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE)



                    fos.write(list_words.toByteArray())
                    fos.flush()
                    fos.close()
                    //printMessage("Sucsses!, File is writed")
                } catch (e: IOException) {
                    //printMessage("Error!, File dont write")
                    //e.printStackTrace()
                }
            }
            "external" -> {
                val myExternalFile = File(getExternalFilesDir(filepath), filename)
                try {
                    val fos: FileOutputStream = FileOutputStream(myExternalFile)
                    fos.write(list_words.toByteArray())
                    fos.flush()
                    fos.close()
                    //printMessage("Sucsses!, File is writed")
                } catch (e: IOException) {
                    //printMessage("Error!, File dont write")
                    //e.printStackTrace()
                }
            }
        }*/
    }

    /*private fun readData(storage: String) {
        when(storage) {
            "external" -> {
                try {
                    val myExternalFile = File(getExternalFilesDir(filepath), filename)
                    val fin = FileInputStream(myExternalFile)
                    var a: Char
                    val temp = StringBuilder()

                    while (fin.read().also { a = it.toChar() } != -1) {
                        temp.append(a)
                        //Log.d("AAAAAA", a.toString())
                    }
                    //val str = temp.toString()
                    tv_fileContent.text = num.toString()
                    fin.close()
                    printMessage("Sucsses!, File is read")
                    printMessage("Absolutaly path is ${myExternalFile.absolutePath}")
                } catch (e: IOException) {
                    printMessage("Error!, File dont read")
                    e.printStackTrace()
                }
            }
            "internal" -> {
                try {
                    //val myInternalFile = File(getDir(filepath, Context.MODE_PRIVATE), filename)
                    //val fin: FileInputStream = FileInputStream(myInternalFile)
                    val fin: FileInputStream = openFileInput(filename)
                    var a: Char
                    val temp = StringBuilder()
                    while (fin.read().also { a = it.toChar() } != -1) {
                        temp.append(a)

                    }
                    //val str = temp.toString()
                    tv_fileContent.text = num.toString()
                    fin.close()
                    printMessage("Sucsses!, File is read")
                    //printMessage("Absolutaly path is ${myInternalFile.absolutePath}")
                    printMessage("Absolutaly path is ${getDir(filename,Context.MODE_PRIVATE)}")
                } catch (e: IOException) {
                    e.printStackTrace()
                    printMessage("Error!, File dont read")
                }
            }
        }

    }*/

}