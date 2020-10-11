package com.product.headlines.headlines.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import javax.inject.Inject

class URLViewModel @Inject constructor(var pApplication: Application) :
    AndroidViewModel(pApplication) {

}