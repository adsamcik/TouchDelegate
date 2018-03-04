package com.adsamcik.toucharea

import android.view.MotionEvent
import android.view.View

/**
 * Abstract Touch Delegate for TouchDelegateComposite
 * This class allows for better overloading of TouchDelegate for TouchDelegateComposite
 * without the need for unnecessary payload that TouchDelegate brings
 */
abstract class AbstractTouchDelegate {
    /**
     * View that is needed for sorting purposes
     */
    abstract val view: View

    /**
     * Touch event that is called
     *
     * @param event Event
     *
     * @return true if the event should not propagate any further
     */
    abstract fun onTouchEvent(event: MotionEvent): Boolean
}