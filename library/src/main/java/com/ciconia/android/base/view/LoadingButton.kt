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
import com.ciconia.android.base.util.isColorDark

class LoadingButton @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var button: Button
    private var progressBar: ProgressBar
    private var text: String? = null
    private var buttonColor: Int = -1
    private var textColor: Int = -1

    companion object {

        @JvmStatic @BindingAdapter("app:onButtonClick")
        fun setOnClickListener(view: LoadingButton, onClick: View.OnClickListener?) {
            view.button.setOnClickListener(onClick)
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.loading_button, this, true)
        button = findViewById(R.id.btn)
        progressBar = findViewById(R.id.pb)
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.LoadingButton, 0, 0)
        text = a.getString(R.styleable.LoadingButton_buttonText)
        buttonColor = a.getColor(R.styleable.LoadingButton_buttonColor, -1)
        textColor = a.getColor(R.styleable.LoadingButton_textColor, -1)


        button.text = text
        if (buttonColor != -1)
            button.backgroundTintList = ColorStateList.valueOf(buttonColor)

        if (textColor != -1) {
            button.setTextColor(textColor)
            progressBar.indeterminateTintList = ColorStateList.valueOf(textColor)
        } else {
            button.setTextColor(if (isColorDark(buttonColor)) Color.WHITE else Color.BLACK)
            progressBar.indeterminateTintList = ColorStateList.valueOf(if (isColorDark(buttonColor)) Color.WHITE else Color.BLACK)
        }
    }

    public fun onStartLoading() {
        button.text = ""
//        button.isEnabled = false
        progressBar.visibility = View.VISIBLE
    }

    public fun onStopLoading() {
//        button.isEnabled = true
        button.text = text
        progressBar.visibility = View.GONE
    }

    public fun isInProgress(): Boolean {
        return progressBar.visibility == View.VISIBLE
    }

    public fun setButtonOnClick(onClick: OnClickListener?) {
        button.setOnClickListener(onClick)
    }
}