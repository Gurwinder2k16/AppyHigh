package com.product.headlines.di.activity.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.product.headlines.headlines.viewmodels.MainViewModel
import com.product.headlines.headlines.viewmodels.URLViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun finSubscriptionViewModel(pFindACarViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(URLViewModel::class)
    internal abstract fun findURLViewModel(pFindACarViewModel: URLViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
