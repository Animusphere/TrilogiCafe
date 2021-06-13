package com.example.trilogicafe.utils
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class XeronTextView(context: Context, attributeSet: AttributeSet) : AppCompatTextView (context,attributeSet) {
    init {
        fontset()
    }

    private fun fontset() {
        val typeface: Typeface = Typeface.createFromAsset(context.assets,"Xeron.ttf")
        setTypeface(typeface)
    }
}