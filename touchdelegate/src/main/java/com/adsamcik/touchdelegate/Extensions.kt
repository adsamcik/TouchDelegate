package com.adsamcik.touchdelegate

import android.graphics.Rect
import android.util.DisplayMetrics

/**
 * Returns screen bounds of the device in pixels
 */
fun DisplayMetrics.getScreenBounds() = Rect(0, 0, widthPixels, heightPixels)

/**
 * Returns true iff the two specified rectangles intersect. In no event are
 * either of the rectangles modified. To record the intersection,
 * use {@link #intersect(Rect)} or {@link #setIntersect(Rect, Rect)}.
 *
 * @param rect The rectangle being tested for intersection
 * @return true iff the two specified rectangles intersect. In no event are
 *              either of the rectangles modified.
 */
fun Rect.intersects(rect: Rect) = Rect.intersects(this, rect)