package com.adsamcik.touchdelegate

import android.graphics.Rect
import android.view.MotionEvent
import android.view.TouchDelegate
import android.view.View
import java.util.*

/**
 * Touch delegate composite is a implementation of TouchDelegate
 * that allows multiple TouchDelegates per view
 */
class TouchDelegateComposite(view: View, var checkVisibility: Boolean = true) : TouchDelegate(emptyRect, view) {
    private val delegates = ArrayList<AbstractTouchDelegate>()

    private val mScreenSize = view.context.resources.displayMetrics.getScreenBounds()

    /**
     * Number of delegates
     */
    val count: Int get() = delegates.size

    /**
     * Add delegate to the composite
     *
     * @param delegate Abstract touch delegate to add
     */
    fun addDelegate(delegate: AbstractTouchDelegate) {
        delegates.add(delegate)
    }

    /**
     * Removes delegate from the composite
     *
     * @param delegate Abstract touch delegate to remove
     */
    fun removeDelegate(delegate: AbstractTouchDelegate) {
        delegates.remove(delegate)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        delegates.sortByDescending { it.view.translationZ }
        delegates.forEach {
            if (it.view.visibility != View.VISIBLE && (!checkVisibility || checkVisibilityOf(it))) {
                event.setLocation(x, y)
                if (it.onTouchEvent(event))
                    return true
            }
        }

        return false
    }

    /**
     * Checks visibility of the delegate
     */
    private fun checkVisibilityOf(touchDelegate: AbstractTouchDelegate): Boolean {
        val rect = Rect()
        touchDelegate.view.getGlobalVisibleRect(rect)
        return mScreenSize.intersects(rect)
    }

    companion object {
        val emptyRect = Rect()

        /**
         * Automatically adds touch delegate to view
         *
         * If touch delegate already exists and is not TouchDelegateComposite it will be
         * replaced with TouchDelegateComposite and added to it so it will still be called
         * Unfortunately there is no way to determine the view it handles so passed view's
         * Z translation will be used for sorting
         */
        fun addTouchDelegateOn(view: View, delegate: AbstractTouchDelegate): TouchDelegateComposite {
            val composite = setTouchDelegateCompositeOn(view)
            composite.addDelegate(delegate)
            return composite
        }

        /**
         * Automatically adds touch delegate to view
         *
         * If touch delegate already exists and is not TouchDelegateComposite it will be
         * replaced with TouchDelegateComposite and added to it so it will still be called
         * Unfortunately there is no way to determine the view it handles so passed view's
         * Z translation will be used for sorting
         */
        fun addTouchDelegate(delegate: AbstractTouchDelegate): TouchDelegateComposite {
            val composite = setTouchDelegateCompositeOn(delegate.view.parent as View)
            composite.addDelegate(delegate)
            return composite
        }

        /**
         * Automatically creates TouchDelegateComposite on the view
         *
         * If touch delegate already exists and is not TouchDelegateComposite it will be
         * replaced with TouchDelegateComposite and added to it so it will still be called
         * Unfortunately there is no way to determine the view it focuses on so it will parent view for sorting
         */
        fun setTouchDelegateCompositeOn(view: View): TouchDelegateComposite {
            val delegate = view.touchDelegate

            if (delegate is TouchDelegateComposite)
                return delegate

            val composite = TouchDelegateComposite(view)
            if (delegate != null)
                composite.addDelegate(WrapperTouchDelegate(delegate, view))

            view.touchDelegate = composite
            return composite
        }
    }
}