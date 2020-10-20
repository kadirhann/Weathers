package com.apps.weather.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel(application: Application): AndroidViewModel(application) {

    protected val compositeDisposable = CompositeDisposable()

    protected val exceptionView: MutableLiveData<Boolean> = MutableLiveData()

    val isLoading = MutableLiveData<Boolean>().apply { value = false }

    open fun getExceptionView() : LiveData<Boolean> = exceptionView

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}