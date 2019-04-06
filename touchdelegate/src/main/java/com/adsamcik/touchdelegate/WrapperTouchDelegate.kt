package com.adsamcik.touchdelegate

import android.view.MotionEvent
import android.view.TouchDelegate
import android.view.View

/**
 * WrapperTouchDelegate is wrapper for the standard TouchDelegate
 * so it can be used in TouchDelegateComposite
 */
class WrapperTouchDelegate(private val delegate: TouchDelegate, override val view: View) : AbstractTouchDelegate {
	override val parentView: View = view.parent as View? ?: throw ExceptionInInitializerError("View does not have a parent, it cannot be rootview!")

	override fun onTouchEvent(event: MotionEvent): Boolean = delegate.onTouchEvent(event)
}