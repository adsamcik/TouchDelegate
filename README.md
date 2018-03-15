 [ ![Download](https://api.bintray.com/packages/adsamcik/android-components/touchdelegate/images/download.svg) ](https://bintray.com/adsamcik/android-components/touchdelegate/_latestVersion)

# TouchDelegate
Touch delegate builds on Android's built in TouchDelegate. TouchDelegate has several limitations and issues. 
For example you can't add more than one TouchDelegate to a parent, this can be quite an issue if you want to have 2 buttons with extended touch areas. There is also DraggableTouchDelegate which tries to solve some translation related issues with default TouchDelegate. 
Android's default TouchDelegate can also be easily used with this library, just wrap it in WrapperTouchDelegate.

This library was originally part of Draggable library but I decided to move it to it's own library so it can be used by other libraries and possibly app itself.

# Usage
Following code automatically adds touch delegate composite to parent view and adds previous delegate and passed delegate to it.

    val rect = Rect(64.dpToPx(), 32.dpToPx(), 0, 32.dpToPx())
    TouchDelegateComposite.addTouchDelegate(DraggableTouchDelegate(rect, extended_button))

# Screenshot
![Screenshot of the example](https://raw.githubusercontent.com/adsamcik/TouchDelegate/master/screenshots/01.png)

Blue area shows the standard button bounds. Pink area shows extended touch area.
