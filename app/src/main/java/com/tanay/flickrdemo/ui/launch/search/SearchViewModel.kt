package com.tanay.flickrdemo.ui.launch.search

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tanay.flickrdemo.App
import com.tanay.flickrdemo.R
import com.tanay.flickrdemo.data.DataManager
import com.tanay.flickrdemo.data.remote.ApiObserver
import com.tanay.flickrdemo.ui.base.BaseViewModel
import com.tanay.flickrdemo.utils.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import javax.inject.Inject

class SearchViewModel @Inject constructor(dataManager: DataManager) :
    BaseViewModel<SearchNavigator>(dataManager) {

    private var searchText = ""
    private var pageNo = 1
    private var totalPages = 0
    private val mutableLiveData = MutableLiveData<ArrayList<String>>()
    var isVisible = ObservableBoolean()

    fun search() {
        isVisible.set(true)
        dataManager.search(text = searchText, pageNo = pageNo.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<String>(compositeDisposable) {
                override fun onSuccess(data: String) {
                    val response = data.substring(14, data.length - 1)

                    val json = JSONObject(response)
                    val photos = json.getJSONObject("photos")
                    totalPages = photos.getInt("pages")
                    pageNo++
                    val array = photos.getJSONArray("photo")
                    if (array.length() == 0) {
                        App.getContext().getString(R.string.no_image).toast()
                    } else {
                        val list = ArrayList<String>()
                        for (i in 0 until array.length()) {
                            if (array.getJSONObject(i).has("url_m")) {
                                list.add(array.getJSONObject(i).getString("url_m"))
                            }

                        }
                        mutableLiveData.value = list
                    }
                    isVisible.set(false)
                }

                override fun onError(e: String?) {
                    isVisible.set(false)
                    App.getContext().getString(R.string.no_image).toast()
                }
            })
    }

    fun afterEmailTextChanged(s: CharSequence) {
        searchText = s.toString()
    }

    fun getPicLiveData(): LiveData<ArrayList<String>> {
        return mutableLiveData
    }

    fun nextPage() {
        if (pageNo < totalPages) {
            search()
        }
    }

    fun clickSearch() {
        if (searchText.isEmpty()) {
            App.getContext().getString(R.string.enter_keyword).toast()
            return
        }
        getNavigator()?.hideKeyboard()
        mutableLiveData.value = null
        search()
    }
}