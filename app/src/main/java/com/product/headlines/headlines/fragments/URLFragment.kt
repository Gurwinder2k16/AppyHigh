package com.product.headlines.headlines.fragments

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.product.headlines.R
import com.product.headlines.headlines.activity.URLActivity
import com.product.headlines.headlines.models.response.ArticlesItem
import com.product.headlines.headlines.viewmodels.URLViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.actionbar_title.*
import kotlinx.android.synthetic.main.u_r_l_fragment.*
import javax.inject.Inject


class URLFragment : DaggerFragment() {

    companion object {
        fun newInstance() = URLFragment()
    }

    private lateinit var viewModel: URLViewModel

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mApplication: Application

    private lateinit var mArticlesItem: ArticlesItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.u_r_l_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, mViewModelFactory).get(URLViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromPrevActivity()
    }

    private fun getDataFromPrevActivity() {
        when (activity?.intent?.hasExtra(URLActivity::class.java.simpleName)!!
                && activity?.intent?.getSerializableExtra(URLActivity::class.java.simpleName) != null
            ) {
            true -> {
                mArticlesItem =
                    activity?.intent?.getSerializableExtra(URLActivity::class.java.simpleName) as ArticlesItem
                title.text = mArticlesItem.title
                loadWebView(mArticlesItem)
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadWebView(mArticlesItem: ArticlesItem) {
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                lyProgress.visibility = View.GONE
                webView.visibility = View.VISIBLE
            }
        }
        webView.loadUrl(mArticlesItem.url)
        ivBack.visibility = View.VISIBLE
        ivBack.setOnClickListener { activity?.let { it.onBackPressed() } }
    }
}