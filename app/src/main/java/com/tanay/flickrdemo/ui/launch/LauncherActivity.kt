package com.tanay.flickrdemo.ui.launch

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tanay.flickrdemo.BR
import com.tanay.flickrdemo.R
import com.tanay.flickrdemo.databinding.ActivityLauncherBinding
import com.tanay.flickrdemo.ui.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class LauncherActivity : BaseActivity<ActivityLauncherBinding, LauncherViewModel>(),LauncherNavigator,HasAndroidInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var launchViewModel: LauncherViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_launcher
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): LauncherViewModel {
        launchViewModel = ViewModelProviders.of(this, viewModelFactory).get(LauncherViewModel::class.java)
        return launchViewModel
    }
    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentDispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchViewModel.setNavigator(this)
    }
}
