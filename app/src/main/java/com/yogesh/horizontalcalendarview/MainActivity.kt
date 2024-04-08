package com.yogesh.horizontalcalendarview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yogesh.calendar.listenter.DateSelectedListener
import com.yogesh.calendar.ui.HorizontalCalendarView
import com.yogesh.horizontalcalendarview.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val horizontalCalendarView: HorizontalCalendarView = findViewById(R.id.calendar)


        // Set listener for date selection
        horizontalCalendarView.setOnDateSelectedListener(object : DateSelectedListener {
            override fun onDateSelected(date: Date) {
                // Handle date selection
                // You can use the selected date here
                Log.d("dat123e", "onDateSelected: $date")
            }
        })
    }
}