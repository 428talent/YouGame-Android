package com.yougame.takayamaaren.yougame.account.fragments.friends

import android.content.Context
import android.graphics.Rect
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yougame.takayamaaren.yougame.R
import android.support.v7.widget.RecyclerView
import android.support.annotation.DimenRes
import android.support.annotation.NonNull



class AccountFriendsAdapter(val datas: MutableList<AccountFriendsItem>) : BaseQuickAdapter<AccountFriendsItem, AccountFriendViewHolder>(R.layout.card_account_friends, datas) {
    override fun convert(helper: AccountFriendViewHolder?, item: AccountFriendsItem?) {

    }
}

class AccountFriendsItem

class AccountFriendViewHolder(itemView: View) : BaseViewHolder(itemView)


class ItemOffsetDecoration(private val mItemOffset: Int) : RecyclerView.ItemDecoration() {

    constructor(context: Context, @DimenRes itemOffsetId: Int) : this(context.resources.getDimensionPixelSize(itemOffsetId))

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset)
    }
}