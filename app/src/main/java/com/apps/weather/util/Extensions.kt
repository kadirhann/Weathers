package com.apps.weather.util

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SetTextI18n")
@BindingAdapter("convertTemp")
fun getTempConvert(view: AppCompatTextView, temp: String){
    view.text = temp.toInt().toString() + " ° C"
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("formatTodayDate")
fun getFormatTodayDate(view: TextView, date: String){
    val pattern = "dd.MM.yyyy"
    val simpleDateFormat = SimpleDateFormat(pattern)
    val date = simpleDateFormat.format(Date())
    view.text = date
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("formatDate")
fun getFormatDate(view: TextView, date: String){
    var spf = SimpleDateFormat("yyyy-MM-dd")
    val newDate = spf.parse(date)
    spf = SimpleDateFormat("dd.MM.yyyy")
    var dateText = spf.format(newDate)
    view.text = dateText
}

@SuppressLint("SetTextI18n")
@BindingAdapter("convertTempDest")
fun getConvertTempDest(view: AppCompatTextView, str: String){

    when (str) {
        "sn" -> {
            view.text = "Snow"
        }
        "sl" -> {
            view.text = "Sleet"
        }
        "h" -> {
            view.text = "Hail"
        }
        "t" -> {
            view.text = "Thunderstorm"
        }
        "hr" -> {
            view.text = "Heavy Rain"
        }
        "lr" -> {
            view.text = "Light Rain"
        }
        "s" -> {
            view.text = "Showers"
        }
        "hc" -> {
            view.text = "Heavy Cloud"
        }
        "lc" -> {
            view.text = "Light Cloud"
        }
        else -> {
            view.text = "Clear"
        }
    }
}
