package com.product.headlines.di.activity.fragment

import com.product.headlines.headlines.fragments.HeadlinesFragment
import com.product.headlines.headlines.fragments.URLFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMainFragment(): HeadlinesFragment

    @ContributesAndroidInjector
    internal abstract fun contributeURLFragment(): URLFragment
}