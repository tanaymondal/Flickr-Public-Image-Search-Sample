package com.tanay.flickrdemo.di

import com.tanay.flickrdemo.ui.launch.LauncherActivity
import com.tanay.flickrdemo.ui.launch.LauncherActivityModule
import com.tanay.flickrdemo.ui.launch.search.SearchFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector(modules = [LauncherActivityModule::class,SearchFragmentProvider::class])
    internal abstract fun bindLauncherActivity(): LauncherActivity
}