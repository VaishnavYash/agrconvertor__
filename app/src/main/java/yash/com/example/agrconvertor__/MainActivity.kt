package yash.com.example.agrconvertor__

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView? = null
    private var result : TextView? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSelectedDate = findViewById(R.id.Displayer)
        result = findViewById(R.id.result)
        val Button2: Button = findViewById(R.id.button2)
        Button2.setOnClickListener {
            clickdatepicker()
        }
    }
    fun clickdatepicker(){
        val mycalender = Calendar.getInstance()
        val year = mycalender.get(Calendar.YEAR)
        val month = mycalender.get(Calendar.MONTH)
        val day = mycalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener{view, selectedyear, selectedmonth, selecteddayOfMonth ->

            val selectedDate = "${selecteddayOfMonth}.${selectedmonth+1}.${selectedyear}"
            tvSelectedDate?.setText(selectedDate) //tvSelectedDate?.Text = selectedDate
            val sds = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
            val Datefinal = sds.parse(selectedDate)
            Datefinal?.let {
                val selectedDateInMinutes = Datefinal.time/86400000       //calculates the time in millisec from 1 jan 1970 to selected date
                val currentDate = sds.parse(sds.format(System.currentTimeMillis()))          //calculates the time in millisec from 1 jan 1970 to Today
                currentDate?.let {
                    val finalDate = currentDate.time/86400000
                    val timetoshow = finalDate - selectedDateInMinutes
                    result?.text = timetoshow.toString()
                }
                }

        },
        year, month, day)

        dpd.datePicker.maxDate = System.currentTimeMillis()-86400000   //to limit the picker not to select the date from future
        dpd.show()

        Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show()
    }
}

