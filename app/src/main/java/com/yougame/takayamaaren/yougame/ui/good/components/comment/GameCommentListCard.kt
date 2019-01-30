package com.yougame.takayamaaren.yougame.ui.good.components.comment

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.card_good_comments.view.*

class GameCommentListCard(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {
    private val adapter: Adapter by lazy {
        Adapter(mutableListOf()).apply {
            bindToRecyclerView(rv_comments)
        }
    }

    fun setSummaryText(text: String) {
        tv_text_summary.text = text
    }

    fun setAverageRating(ratingText: String) {
        tv_rating_average.text = ratingText
    }

    init {
        View.inflate(context, R.layout.card_good_comments, this)
    }

    fun updateComments(comments: List<CommentItem>) {
        adapter.setNewData(comments.toMutableList())
    }
}