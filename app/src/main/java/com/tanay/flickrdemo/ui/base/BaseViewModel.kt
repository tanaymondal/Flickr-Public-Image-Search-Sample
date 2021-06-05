package com.tanay.flickrdemo.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tanay.flickrdemo.data.DataManager
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

open class BaseViewModel<N>(
    val dataManager: DataManager
): ViewModel() {


    private var mNavigator: WeakReference<N>? = null
    val loader: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun displayLoader(isLoading: Boolean) {
        loader.value = isLoading
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


    fun getNavigator(): N? {
        return mNavigator?.get()
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }
}