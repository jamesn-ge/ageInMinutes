package com.starsolns.ageapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

    lateinit var pickedTextView: TextView
    lateinit var ageDisplayTv: TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pickDateButton = findViewById<Button>(R.id.pickDateBtn)


       pickDateButton.setOnClickListener { view->
           pickDate(view)
       }
    }

    fun pickDate(view: View){

        val cal = Calendar.getInstance()
        var year = cal.get(Calendar.YEAR)
        var month = cal.get(Calendar.MONTH)
        var dayOfM = cal.get(Calendar.DAY_OF_MONTH)

        var dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            pickedTextView = findViewById(R.id.selectedDateTxt)
            pickedTextView.setText(selectedDate)
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            //get the selected date in minutes
            var selectedTimeInMinutes = theDate!!.time / 60000

            //get the current date
            var currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            var currentTimeinMinutes = currentDate!!.time / 60000

            var diffTimeInMinutes = currentTimeinMinutes - selectedTimeInMinutes

            //display age in minutes
            ageDisplayTv = findViewById(R.id.ageInMinutes)
            ageDisplayTv.setText(diffTimeInMinutes.toString())

        },
                year, month, dayOfM)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }

}