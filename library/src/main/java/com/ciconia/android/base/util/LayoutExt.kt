package com.ciconia.android.base.util

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout

fun Context.linearLayoutParams(width: Int?, height: Int?, left: Int, top: Int, right: Int, bottom: Int): LinearLayout.LayoutParams {
    var params: LinearLayout.LayoutParams = if (width == null || height == null)
        LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    else
        LinearLayout.LayoutParams(width, height)
    params.setMargins(dpToPx(left), dpToPx(top), dpToPx(right), dpToPx(bottom))
    return params
}