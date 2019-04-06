package com.adsamcik.touchdelegate

import android.view.MotionEvent
import android.view.View

/**
 * Abstract Touch Delegate for TouchDelegateComposite
 * This class allows for better overloading of TouchDelegate for TouchDelegateComposite
 * without the need for unnecessary payload that TouchDelegate brings
 */
interface AbstractTouchDelegate {
	/**
	 * View that is needed for sorting purposes
	 */
	val view: View

	/**
	 * Parent view is required so TouchDelegateComposite can be added to it
	 */
	val parentView: View

	/**
	 * Touch event that is called
	 *
	 * @param event Event
	 *
	 * @return true if the event should not propagate any further
	 */
	fun onTouchEvent(event: MotionEvent): Boolean
}