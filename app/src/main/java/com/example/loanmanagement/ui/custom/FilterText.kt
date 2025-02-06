package com.example.loanmanagement.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.loanmanagement.R

class FilterText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var isSelectedState: Boolean = false

    init {
        setPadding(0, 0, 0, 0)
        gravity = Gravity.CENTER
        setTextAppearance(context, R.style.TextAppearance_FilterText)
    }

    fun setSelectedState(isSelected: Boolean) {
        this.isSelectedState = isSelected
        updateState()
    }

    private fun updateState() {
        // Apply custom background and text color based on selection
        if (isSelectedState) {
            background = ContextCompat.getDrawable(context, R.drawable.bg_rounded_primary_48)
            setTextColor(ContextCompat.getColor(context, R.color.white))
        } else {
            background = ContextCompat.getDrawable(context, R.drawable.bg_rounded_stroke_48)
            setTextColor(ContextCompat.getColor(context, R.color.color_text_primary))
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        updateState()
    }
}