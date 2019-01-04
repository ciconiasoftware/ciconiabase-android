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

    companion object {

        var onButtonClickListener: View.OnClickListener? = null

        @JvmStatic @BindingAdapter("android:onButtonClick")
        fun setOnClickListener(view: View, onClick: View.OnClickListener?) {
            if (onClick != null) {
                onButtonClickListener = onClick
            }
        }

        @JvmStatic @BindingAdapter("app:buttonBackgroundColor")
        fun setButtonBackground(view: LoadingButton, hexColorValue: Int) {
            view.button.backgroundTintList = ColorStateList.valueOf(hexColorValue)
            view.button.setTextColor(if (isColorDark(hexColorValue)) Color.WHITE else Color.BLACK)
            view.progressBar.indeterminateTintList = ColorStateList.valueOf(if (isColorDark(hexColorValue)) Color.WHITE else Color.BLACK)

        }

        @JvmStatic @BindingAdapter("app:buttonText")
        fun setButtonText(view: LoadingButton, text: String) {
            view.text = text
            view.button.text = text
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.loading_button, this, true)
        button = findViewById(R.id.btn)
        progressBar = findViewById(R.id.pb)
        button.setOnClickListener { v ->
            onButtonClickListener?.let {
                if (!isInProgress())
                    it.onClick(v)
            }
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


}