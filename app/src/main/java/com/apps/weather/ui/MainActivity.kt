package com.apps.weather.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.apps.weather.R
import com.apps.weather.common.BaseActivity
import com.apps.weather.data.model.Location
import com.apps.weather.databinding.ActivityMainBinding
import com.apps.weather.ui.adapter.CitiesAdapter
import com.apps.weather.ui.adapter.DaysAdapter
import com.apps.weather.ui.view.MainViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import es.dmoral.toasty.Toasty

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class.java),
 CitiesAdapter.OnItemClickListener{

    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    private val adapterCities = CitiesAdapter()

    private val adapterDays = DaysAdapter()

    private var pos = 0

    override fun initViewModel(viewModel: MainViewModel) {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        init()
    }

    override fun getLayoutRes() = R.layout.activity_main

    private fun init() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        binding.recyclerViewCity.adapter = adapterCities
        binding.recyclerViewDays.adapter = adapterDays
        viewModel.todayLiveData.value

        viewModel.getLocationListLiveData().observe(this, { result ->
            adapterCities.setList(result)
            adapterCities.setOnItemClickListener(this)
        })

        viewModel.getlocationWeatherLiveData().observe(this, { result ->
            result.consolidated_weather?.let { it1 -> adapterDays.setList(it1) }
                .also {
                   binding.recyclerViewCity.smoothScrollToPosition(pos)
                   viewModel.todayLiveData.value = result.consolidated_weather?.get(0)
            }
        })

        viewModel.getExceptionView().observe(this, {
            Toasty.warning(this@MainActivity,
                resources.getString(R.string.network_error),
                Toast.LENGTH_LONG, true).show()
        })

    }

    override fun onClickListener(result: Location, position: Int) {

        pos=position
        viewModel.fetchNewsList(resources.getString(R.string.main_base_url) + result.woeid + "/")

        val bundle = Bundle()
        bundle.putString("showCityName", result.title)
        mFirebaseAnalytics?.logEvent("show_selected", bundle)

    }

}
