package com.example.effectivemobiletesttask.presentation.tickets.adapters.decorators

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RightSpaceItemDecoration(private val rightSpaceDp: Int, context: Context) : RecyclerView.ItemDecoration() {
    private val rightSpacePx = context.dpToPx(rightSpaceDp)

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)

        if (position != parent.adapter!!.itemCount - 1) {
            outRect.right = rightSpacePx
        }
    }
}

