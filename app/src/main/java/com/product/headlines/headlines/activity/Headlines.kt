package com.product.headlines.headlines.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.product.headlines.R
import com.product.headlines.core.bases.BaseActivity
import com.product.headlines.headlines.fragments.HeadlinesDetailFragment
import com.product.headlines.headlines.fragments.HeadlinesFragment
import com.product.headlines.headlines.models.response.ArticlesItem
import dagger.android.AndroidInjection

class Headlines : BaseActivity() {

    var mDefaultFragment = HeadlinesFragment.newInstance()

    fun getDefaultFragment() = mDefaultFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.headlines_activity)
        if (savedInstanceState == null) {
            setDefaultHeadlineRoute(mDefaultFragment)
        }
    }

    private fun setDefaultHeadlineRoute(
        pFragment: Fragment,
        pAdd: Boolean = false,
        pSharedView: View? = null
    ) {
        supportFragmentManager.beginTransaction().let { it ->
            when (pAdd) {
                true -> {
                    it.add(R.id.container, pFragment)
                    it.addToBackStack(pFragment::class.java.simpleName)
                }
                false -> {
                    it.replace(R.id.container, pFragment)
                }
            }
        }.commit()
    }

    fun setDetailFragment(pArticlesItem: ArticlesItem, pSharedView: View) {
        val mHeadlinesDetailFragment = HeadlinesDetailFragment.newInstance()
        mHeadlinesDetailFragment.setSelectedArticleItem(pArticlesItem)
        setDefaultHeadlineRoute(mHeadlinesDetailFragment, true, pSharedView)
    }
}