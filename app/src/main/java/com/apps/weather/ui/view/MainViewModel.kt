package com.apps.weather.ui.view

import android.app.Application
import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData
import com.apps.weather.common.BaseViewModel
import com.apps.weather.data.model.Location
import com.apps.weather.data.model.LocationDetail
import com.apps.weather.data.model.LocationResult
import com.apps.weather.data.remote.LocationDataSource
import com.mobilion.tuborgorion.data.Status
import com.orhanobut.hawk.Hawk
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : BaseViewModel(application) {

    private val locationDataSource = LocationDataSource()

    private val emptyResult = LocationResult()

    private val locationListLiveData: MutableLiveData<List<Location>> = MutableLiveData()
    fun getLocationListLiveData(): LiveData<List<Location>> = locationListLiveData

    private val locationWeatherLiveData: MutableLiveData<LocationDetail> = MutableLiveData()
    fun getlocationWeatherLiveData(): LiveData<LocationDetail> = locationWeatherLiveData

    val todayLiveData: MutableLiveData<LocationResult> = MutableLiveData()
    var locationString : String = "41.0751751,28.9877938"

    init {
        locationString= Hawk.get("location")
        fetchLocationList(locationString)
        todayLiveData.value = emptyResult
    }

    private fun fetchLocationList(latLng: String) {

        compositeDisposable.add(
            locationDataSource
                .fetchLocationList(latLng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    when (it.status) {
                        Status.SUCCESS -> {
                            locationListLiveData.value = it.data
                        }
                        Status.ERROR -> {
                            exceptionView.value = true
                            isLoading.postValue(false)
                        }
                        Status.LOADING -> {
                            isLoading.postValue(true)
                        }
                    }
                })
    }

    fun fetchNewsList(id: String) {

        compositeDisposable.add(
            locationDataSource
                .fetchLocationDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    when (it.status) {
                        Status.SUCCESS -> {
                            isLoading.postValue(false)
                            locationWeatherLiveData.value = it.data
                        }
                        Status.ERROR -> {
                            exceptionView.value = true
                            isLoading.postValue(false)
                        }
                        Status.LOADING -> {
                            isLoading.postValue(true)
                        }
                    }
                })
    }


}