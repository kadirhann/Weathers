package com.apps.weather.data.remote

import com.apps.weather.data.Resource
import com.apps.weather.data.model.Location
import com.apps.weather.data.model.LocationDetail
import com.apps.weather.data.remote.ServiceProvider
import io.reactivex.Emitter
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class LocationDataSource {

    private val serviceProvider = ServiceProvider()

    /*
     Location repository
    */


    fun fetchLocationList(latLng: String): Observable<Resource<List<Location>>> {
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading())
            serviceProvider
                .apiService
                .getLocationList(latLng)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        emitter.onNext(Resource.success(it))
                        emitter.onComplete()
                    },
                    {
                        emitter.onNext(Resource.error(it.message ?: "Error"))
                        emitter.onComplete()
                    }
                )
        }
    }

    fun fetchLocationDetail(id: String): Observable<Resource<LocationDetail>> {
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading())
            serviceProvider
                .apiService
                .getLocationDetail(id)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        emitter.onNext(Resource.success(it))
                        emitter.onComplete()
                    },
                    {
                        emitter.onNext(Resource.error(it.message ?: "Error"))
                        emitter.onComplete()
                    }
                )
        }
    }
}

private fun <T> Emitter<T>.onNext(success: Resource<List<Location>>) {

}
