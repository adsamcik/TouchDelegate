package com.adsamcik.draggable.delegates

import android.graphics.Rect
import android.view.MotionEvent
import android.view.View

internal class DraggableTouchDelegate(private val mOffsetRect: Rect, override val view: View) : AbstractTouchDelegate() {
    private val mHitRect = Rect()
    private var mDelegateTargeted = false

    private fun updateHitRect() {
        //view.getHitRect(mOffsetRect)
        val tX = view.x.toInt()
        val tY = view.y.toInt()
        mHitRect.left = tX - mOffsetRect.left
        mHitRect.top = tY - mOffsetRect.top
        mHitRect.right = tX + view.width + mOffsetRect.right
        mHitRect.bottom = tY + view.height + mOffsetRect.bottom
    }

    /**
     * Updates offsets of the delegate
     */
    fun updateOffsets(left: Int, top: Int, right: Int, bottom: Int) {
        mOffsetRect.left = left
        mOffsetRect.top = top
        mOffsetRect.right = right
        mOffsetRect.bottom = bottom
    }

    /**
     * Updates offsets of the delegate
     */
    fun updateOffsets(rect: Rect) {
        mOffsetRect.left = rect.left
        mOffsetRect.top = rect.top
        mOffsetRect.right = rect.right
        mOffsetRect.bottom = rect.bottom
    }

    /**
     * Called when touch event occurs
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        updateHitRect()
        var sendToDelegate = false
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                if (mDelegateTargeted) {
                    mDelegateTargeted = false
                    sendToDelegate = true
                }
            }
            MotionEvent.ACTION_DOWN -> {
                if (mHitRect.contains(event.x.toInt(), event.y.toInt())) {
                    mDelegateTargeted = true
                    sendToDelegate = true
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (mDelegateTargeted)
                    sendToDelegate = true
            }
            MotionEvent.ACTION_CANCEL -> {
                mDelegateTargeted = false
                sendToDelegate = true
            }
        }

        if (sendToDelegate)
            return view.dispatchTouchEvent(event)

        return false
    }

}