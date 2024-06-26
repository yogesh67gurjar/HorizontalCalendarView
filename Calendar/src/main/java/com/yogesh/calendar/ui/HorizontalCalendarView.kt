package com.yogesh.calendar.ui

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogesh.calendar.adapter.CalendarAdapter
import com.yogesh.calendar.listenter.DateSelectedListener
import com.yogesh.calendar.model.HorizontalCalendarDate
import java.util.Calendar
import java.util.Date

class HorizontalCalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    private var dates: List<HorizontalCalendarDate> = mutableListOf()
    private var onDateSelectedListener: DateSelectedListener? = null
    private var selectedPosition = NO_POSITION

    init {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        setLayoutManager(layoutManager)
        listOfDates()
    }

    private fun listOfDates() {
        // Create a list of dates
        val dates = mutableListOf<HorizontalCalendarDate>()
        val calendar = Calendar.getInstance()
        for (i in 0 until 30) {
            dates.add(HorizontalCalendarDate(calendar.time))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        setDates(dates)
    }

    private fun setDates(dates: List<HorizontalCalendarDate>) {
        this.dates = dates

        // Set today's date as default selected date
        val today = Date()
        for (i in dates.indices) {
            if (dates[i].isSameDay(today)) {
                selectedPosition = i
                break
            }
        }
        adapter = CalendarAdapter(dates) { position -> selectDate(position) }
    }

    fun setOnDateSelectedListener(listener: DateSelectedListener?) {
        onDateSelectedListener = listener
    }

    private fun selectDate(position: Int) {
        selectedPosition = position
        // Notify adapter that data has changed and update selection
        (adapter as? CalendarAdapter)?.setSelectedPosition(position)

        onDateSelectedListener?.onDateSelected(dates[position].date)
    }
}