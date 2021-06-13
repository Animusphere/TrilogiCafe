package com.example.trilogicafe.utils
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class TCButton (context: Context, attributeSet: AttributeSet):
AppCompatButton(context,attributeSet){
    init {
        fontset()
    }

    private fun fontset() {
        val typeface: Typeface = Typeface.createFromAsset(context.assets,"XSSN.ttf")
        setTypeface(typeface)
    }
}