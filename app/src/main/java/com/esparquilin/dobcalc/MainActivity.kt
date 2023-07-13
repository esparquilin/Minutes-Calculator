package com.esparquilin.dobcalc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {




    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSelectedDate= findViewById(R.id.selectedDate)
        timeInMinutes=findViewById(R.id.timeInMinutes)
          val btnDatePicker:Button=findViewById(R.id.buttonDatePicker)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }

    }

   @SuppressLint("SetTextI18n")
   @RequiresApi(Build.VERSION_CODES.N)
   private fun clickDatePicker(){


       Calendar.YEAR
       val myCalendar=Calendar.getInstance()
       val year=myCalendar.get(Calendar.YEAR)
       val month=myCalendar.get(Calendar.MONTH)
       val day=myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
        {_, selectedYear, selectedMonth, selectedDayOfMonth->

            val selectedDate= "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate?.text=selectedDate

            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate= sdf.parse(selectedDate)

            val selectedDateInMinutes= theDate!!.time/6000

            val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateInMinutes= currentDate!!.time/6000
            val differenceInMinutes= currentDateInMinutes - selectedDateInMinutes
            timeInMinutes?.text=differenceInMinutes.toString()

        },
           year, month, day
           )
       dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
       dpd.show()

    }

    private var tvSelectedDate:TextView?=null
    private var timeInMinutes:TextView?=null

}