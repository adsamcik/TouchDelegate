package com.adsamcik.touchdelegate

import android.graphics.Rect
import android.util.DisplayMetrics

/**
 * Returns screen bounds of the device in pixels
 */
fun DisplayMetrics.getScreenBounds() = Rect(0, 0, widthPixels, heightPixels)