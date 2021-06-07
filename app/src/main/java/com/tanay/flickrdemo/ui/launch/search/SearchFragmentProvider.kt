package com.tanay.flickrdemo.ui.launch.search

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideSearchFragmentFactory(): SearchFragment
}