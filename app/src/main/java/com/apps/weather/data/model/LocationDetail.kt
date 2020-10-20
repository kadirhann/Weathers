package com.apps.weather.data.model

data class LocationDetail(

    var title: String? = "",
    var timezone: String? = "",
    var parent: Parent? = null,
    val consolidated_weather: List<LocationResult>?
)

data class Parent(
    var title: String? = "",
    var location_type: String? = "",
)

data class LocationResult(
    var id: Long ? = 0,
    var weather_state_name: String? = "",
    var weather_state_abbr: String? = "",
    var wind_direction_compass: String? = "",
    var applicable_date: String? = "",
    var min_temp: Double? = 0.0,
    var max_temp: Double? = 0.0,
    var the_temp: Double? = 0.0,
    var wind_speed: Double? = 0.0,
    var humidity: Double? = 0.0,
    var visibility: Double? = 0.0,
    var air_pressure: Double? = 0.0
)
