package com.product.headlines.di.activity

import com.product.headlines.headlines.activity.Headlines
import com.product.headlines.headlines.activity.URLActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeHomeActivity(): Headlines

    @ContributesAndroidInjector()
    abstract fun contributeURLActivity(): URLActivity

}
