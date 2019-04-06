package com.adsamcik.touchareatest

import android.graphics.Rect
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.adsamcik.touchdelegate.DraggableTouchDelegate
import com.adsamcik.touchdelegate.TouchDelegateComposite
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
	private fun Int.dpToPx() = (this * resources.displayMetrics.density).roundToInt()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		//Set how far will the delegate extend touch on each side
		val rect = Rect(64.dpToPx(), 32.dpToPx(), 0, 32.dpToPx())

		//Add delegate to view
		TouchDelegateComposite.addTouchDelegate(DraggableTouchDelegate(rect, extended_button))
		extended_button.setOnClickListener {
			AlertDialog.Builder(this).setMessage("Clicked extended").show()
		}

		TouchDelegateComposite.addTouchDelegate(DraggableTouchDelegate(rect, obscured_view))
		obscured_view.setOnClickListener {
			AlertDialog.Builder(this).setMessage("Clicked obscured").show()
		}

		//For now only views that are not drawn are detected by visibility check. Other views must be obscured like this
		TouchDelegateComposite.addTouchDelegate(DraggableTouchDelegate(rect, obscuring_view))
		obscuring_view.setOnClickListener {}
	}
}
