package com.adsamcik.touchdelegate

import android.graphics.Rect
import android.util.DisplayMetrics

fun DisplayMetrics.getScreenSize() = Rect(0, 0, widthPixels, heightPixels)