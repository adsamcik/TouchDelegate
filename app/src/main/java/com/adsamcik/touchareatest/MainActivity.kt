package com.adsamcik.touchareatest

import android.graphics.Rect
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.view.MotionEvent
import com.adsamcik.touchdelegate.DraggableTouchDelegate
import com.adsamcik.touchdelegate.TouchDelegateComposite
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private fun Int.dpToPx() = (this * resources.displayMetrics.density).roundToInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rect = Rect(64.dpToPx(), 32.dpToPx(), 0, 32.dpToPx())
        TouchDelegateComposite.addTouchDelegate(DraggableTouchDelegate(rect, extended_button))
        val dialog = AlertDialog.Builder(this).setMessage("Clicked").create()
        extended_button.setOnClickListener {
            dialog.show()
        }

        TouchDelegateComposite.addTouchDelegate(DraggableTouchDelegate(rect, obscured_view))
        obscured_view.setOnClickListener {
            dialog.show()
        }

        obscuring_view.setOnClickListener {
        }
    }
}
