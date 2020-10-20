package com.apps.weather.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.apps.weather.R

@BindingAdapter("url")
fun loadImage(view: ImageView, url: String?) {

    if(url.isNullOrEmpty()){
        return
    }

    else {

        when (url) {
            "sn" -> {
                view.setBackgroundResource(R.drawable.ic_snowing)
            }
            "sl" -> {
                view.setBackgroundResource(R.drawable.ic_sleet)
            }
            "h" -> {
                view.setBackgroundResource(R.drawable.ic_hail)
            }
            "t" -> {
                view.setBackgroundResource(R.drawable.ic_thunderstorm)
            }
            "hr" -> {
                view.setBackgroundResource(R.drawable.ic_rainy)
            }
            "lr" -> {
                view.setBackgroundResource(R.drawable.ic_light_rain)
            }
            "s" -> {
                view.setBackgroundResource(R.drawable.ic_showes)
            }
            "hc" -> {
                view.setBackgroundResource(R.drawable.ic_cloud)
            }
            "lc" -> {
                view.setBackgroundResource(R.drawable.ic_ligth_clouds)
            }
            else -> {
                view.setBackgroundResource(R.drawable.ic_default_weather_icn)
            }

        }
    }
}