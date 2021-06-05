package com.tanay.flickrdemo.utils

class Urls {
    companion object {

        // https://www.flickr.com/services/rest/?
        // method=flickr.photos.search
        // &api_key=04a11f92f638310ca1bc72b1426ec95b
        // &text=animal
        // &extras=url_m
        // &media=photos
        // &format=json
        // &per_page=30
        // &page=1
        // &content_type=1

        const val BASE_URL = "https://www.flickr.com/services/rest/"
        const val SEARCH = "?method=flickr.photos.search" +
                "&api_key=${AppConstants.FLICKR_API_KEY}" +
                "&extras=url_m" +
                "&media=photos" +
                "&format=json" +
                "&per_page=30" +
                "&content_type=1"


    }
}