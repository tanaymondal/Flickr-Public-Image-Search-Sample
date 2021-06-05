package com.tanay.flickrdemo.ui.launch

import com.tanay.flickrdemo.data.DataManager
import com.tanay.flickrdemo.ui.base.BaseViewModel
import javax.inject.Inject

class LauncherViewModel @Inject constructor(dataManager: DataManager): BaseViewModel<LauncherNavigator>(dataManager) {

}