package com.ciconia.android.base.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.widget.FrameLayout
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import com.ciconia.android.base.R
import com.ciconia.android.base.util.adjustAlpha
import com.ciconia.android.base.util.isColorDark
import android.util.TypedValue




class LoadingButton @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var button: Button
    private var progressBar: ProgressBar
    private var text: String? = null
    private var buttonColor: Int = 0
    private var textColor: Int = 0

    companion object {

        @JvmStatic
        @BindingAdapter("app:onButtonClick")
        fun setOnClickListener(view: LoadingButton, onClick: View.OnClickListener?) {
            view.button.setOnClickListener(onClick)
        }

        @JvmStatic
        @BindingAdapter("app:buttonColor")
        fun setButtonColor(view: LoadingButton, color: Int) {
            view.buttonColor = color
            view.drawButton()
        }

        @JvmStatic
        @BindingAdapter("app:textColor")
        fun setTextColor(view: LoadingButton, color: Int) {
            view.textColor = color
            view.drawButton()
        }

        @JvmStatic
        @BindingAdapter("app:buttonText")
        fun setButtonText(view: LoadingButton, text: String) {
            view.text = text
            view.button.text = text
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.loading_button, this, true)
        button = findViewById(R.id.btn)
        progressBar = findViewById(R.id.pb)
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.LoadingButton, 0, 0)
        text = a.getString(R.styleable.LoadingButton_buttonText)
        buttonColor = a.getColor(R.styleable.LoadingButton_buttonColor, fetchAccentColor())
        textColor = a.getColor(R.styleable.LoadingButton_textColor, 0)

        drawButton()
    }

    fun setButtonOnClickListener(onClick: View.OnClickListener?) {
        button.setOnClickListener(onClick)
    }

    fun setButtonColor(color: Int) {
        buttonColor = color
        drawButton()
    }

    fun setTextColor(color: Int) {
        textColor = color
        drawButton()
    }

    fun setButtonText(text: String) {
        this.text = text
        button.text = text
    }

    fun setButtonEnabled(isEnabled: Boolean) {
        button.isEnabled = isEnabled
        when {
            !isEnabled -> button.setTextColor(Color.GRAY)
            textColor != 0 -> {
                button.setTextColor(textColor)
                progressBar.indeterminateTintList = ColorStateList.valueOf(textColor)
            }
            else -> {
                button.setTextColor(if (isColorDark(buttonColor)) Color.WHITE else Color.BLACK)
                progressBar.indeterminateTintList = ColorStateList.valueOf(if (isColorDark(buttonColor)) Color.WHITE else Color.BLACK)
            }
        }
    }

    private fun drawButton() {
        val colorStateList = ColorStateList(
                arrayOf(
                        intArrayOf(android.R.attr.state_enabled),
                        intArrayOf(-android.R.attr.state_enabled)
                ),
                intArrayOf(
                        buttonColor,
                        adjustAlpha(buttonColor, 0.3f)
                )
        )
        button.backgroundTintList = colorStateList


        button.text = text


        if (textColor != 0) {
            button.setTextColor(textColor)
            progressBar.indeterminateTintList = ColorStateList.valueOf(textColor)
        } else {
            button.setTextColor(if (isColorDark(buttonColor)) Color.WHITE else Color.BLACK)
            progressBar.indeterminateTintList = ColorStateList.valueOf(if (isColorDark(buttonColor)) Color.WHITE else Color.BLACK)
        }
    }

    public fun onStartLoading() {
        button.text = ""
        button.isClickable = false
        progressBar.visibility = View.VISIBLE
    }

    public fun onStopLoading() {
        button.isClickable = true
        button.text = text
        progressBar.visibility = View.GONE
    }

    public fun isInProgress(): Boolean {
        return progressBar.visibility == View.VISIBLE
    }

    public fun setButtonOnClick(onClick: OnClickListener?) {
        button.setOnClickListener(onClick)
    }

    private fun fetchAccentColor(): Int {
        val typedValue = TypedValue()

        val a = context.obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorAccent))
        val color = a.getColor(0, 0)

        a.recycle()

        return color
    }
}