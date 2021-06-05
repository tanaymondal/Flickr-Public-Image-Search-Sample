package com.tanay.flickrdemo.data.remote

import com.tanay.flickrdemo.utils.Urls
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiHelper {
    @GET(Urls.SEARCH)
    fun search(@Query("text") text: String, @Query("page") pageNo: String): Observable<String>
}