package com.example.trilogicafe.utils
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class TCEditText (context: Context, attributeSet: AttributeSet):
    AppCompatEditText(context,attributeSet){
    init {
        fontset()
    }

    private fun fontset() {
        val typeface: Typeface = Typeface.createFromAsset(context.assets,"XSSN.ttf")
        setTypeface(typeface)
    }
}