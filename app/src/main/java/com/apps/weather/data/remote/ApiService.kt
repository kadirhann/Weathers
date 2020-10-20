package com.apps.weather.data.remote

import com.apps.weather.data.model.Location
import com.apps.weather.data.model.LocationDetail
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @GET("api/location/search")
    fun getLocationList(@Query("lattlong") latLng: String): Single<List<Location>>

    @GET
    fun getLocationDetail(@Url url: String): Single<LocationDetail>
}