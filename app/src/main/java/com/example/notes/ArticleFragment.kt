package com.example.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope

class ArticleFragment : Fragment() {

    companion object {
        private const val ID_KEY = "id"
        const val TAG = "ArticleFragment"

        fun newInstance(articleId: Int): Fragment {
            val fragment = ArticleFragment()

            val arguments = Bundle()
            arguments.putInt(ID_KEY, articleId)
            fragment.arguments = arguments

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = checkNotNull(arguments) { "Arguments are required" }
        val id = arguments.getInt(ID_KEY)
        displayDetails(id)
    }

    private fun displayDetails(articleId : Int) {
        viewLifecycleOwner.lifecycleScope.launch() {
            txvText.text = MainActivity.articlesStorage.getArticleById(articleId).text
            imgArticle.setImageResource(MainActivity.articlesStorage.getArticleById(articleId).photoId)
        }
    }
}
