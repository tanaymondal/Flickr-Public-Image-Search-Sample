package com.tanay.flickrdemo.data

import com.tanay.flickrdemo.data.remote.ApiHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(private val api: ApiHelper) : DataManager {

    override fun search(text: String, pageNo: String) = api.search(text, pageNo)
}