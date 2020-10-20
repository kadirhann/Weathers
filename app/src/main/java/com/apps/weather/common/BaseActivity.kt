package com.apps.weather.common


import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>(
    private val mViewModelClass: Class<VM>) : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    val viewModel by lazy {
        ViewModelProviders.of(this,ViewModelProvider.AndroidViewModelFactory(application)).get(mViewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel(viewModel)
        super.onCreate(savedInstanceState)
    }

    abstract fun initViewModel(viewModel: VM)


    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }


}

