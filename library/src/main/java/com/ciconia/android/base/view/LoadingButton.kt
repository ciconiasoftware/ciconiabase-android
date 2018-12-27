package com.ciconia.android.base.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.ciconia.android.base.R

class LoadingButton @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var button: Button
    private var progressBar: ProgressBar
    private var text: String

    companion object {

        var onButtonClickListener: View.OnClickListener? = null

        @JvmStatic @BindingAdapter("android:onButtonClick")
        fun setOnClickListener(view: View, onClick: View.OnClickListener?) {
            if (onClick != null) {
                onButtonClickListener = onClick
            }
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.loading_button, this, true)
        button = findViewById(R.id.btn)
        progressBar = findViewById(R.id.pb)
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.LoadingButton, 0, 0)
        text = a.getString(R.styleable.LoadingButton_text)
        button.text = text
        button.setOnClickListener { v ->
            onButtonClickListener?.let {
                it.onClick(v)
            }
        }
    }

    public fun onStartLoading() {
        button.text = ""
        button.isEnabled = false
        progressBar.visibility = View.VISIBLE
    }

    public fun onStopLoading() {
        button.isEnabled = true
        button.text = text
        progressBar.visibility = View.GONE
    }


}