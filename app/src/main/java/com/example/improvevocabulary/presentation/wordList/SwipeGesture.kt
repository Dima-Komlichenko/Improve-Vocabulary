package com.example.improvevocabulary.presentation.wordList

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.improvevocabulary.R
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


abstract class SwipeGesture(context: Context?): ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    private val removeIcon = R.drawable.ic_remove_gray
    private val moveIcon = R.drawable.ic_move_gray
    private lateinit var  simpleItemTouchCallback: ItemTouchHelper.SimpleCallback

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            .addSwipeLeftActionIcon(removeIcon)
            .addSwipeRightActionIcon(moveIcon)
            .create()
            .decorate()
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}