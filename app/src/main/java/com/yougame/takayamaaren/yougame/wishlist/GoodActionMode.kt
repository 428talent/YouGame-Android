package com.yougame.takayamaaren.yougame.wishlist

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import com.yougame.takayamaaren.yougame.R

class GoodActionMode : ActionMode.Callback {
    var onExitMode: (() -> Unit)? = null
    var onSelectAll: (() -> Unit)? = null
    var onDeselectAll: (() -> Unit)? = null
    var onDelete: ((mode: ActionMode) -> Unit)? = null
    private var isSelectAll = false
    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_wishlist_action_select_all -> {
                takeIf {
                    isSelectAll
                }?.let {
                    onDeselectAll?.invoke()
                } ?: run {
                    onSelectAll?.invoke()

                }
                isSelectAll = !isSelectAll
            }
            R.id.menu_wishlist_action_delete -> {
                onDelete?.invoke(mode)
            }
        }
        return true
    }

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        mode.menuInflater.inflate(R.menu.menu_wishlist_good_action, menu)
        mode.title = "商品操作"


        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode) {
        onExitMode?.invoke()
    }
}