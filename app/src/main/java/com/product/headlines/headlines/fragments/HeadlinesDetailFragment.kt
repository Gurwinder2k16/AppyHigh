package com.product.headlines.headlines.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.product.headlines.R
import com.product.headlines.headlines.activity.URLActivity
import com.product.headlines.headlines.models.response.ArticlesItem
import kotlinx.android.synthetic.main.detail_fragment.*

class HeadlinesDetailFragment : Fragment() {

    companion object {
        fun newInstance() = HeadlinesDetailFragment()
    }

    private lateinit var mArticlesItem: ArticlesItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDetailView()
    }

    fun setSelectedArticleItem(pArticlesItem: ArticlesItem) {
        mArticlesItem = pArticlesItem
    }

    private fun setDetailView() {
        when (::mArticlesItem.isInitialized) {
            true -> {
                tvHeadLineTitle.text = mArticlesItem.title
                tvSubtitle.text = mArticlesItem.author?.trim()
                tvDate.text = mArticlesItem.publishedAt?.trim()
                tvDetail.text = mArticlesItem.content?.trim()
                Glide.with(context!!)
                    .load(mArticlesItem.urlToImage)
                    .centerCrop()
                    .into(ivHeadLineImage)
            }
        }
        ivBack.setOnClickListener { activity.let { it?.onBackPressed() } }
        tvReadMore.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(URLActivity::class.java.simpleName, mArticlesItem)
            val intent = Intent(activity, URLActivity::class.java)
            intent.putExtras(bundle)
            activity?.let { startActivity(intent) }
        }
    }

}