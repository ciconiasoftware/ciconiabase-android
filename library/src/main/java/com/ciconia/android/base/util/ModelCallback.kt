package com.ciconia.android.base.util

import android.os.Bundle
import android.view.View

interface ModelCallback {
    fun onModelResult(view: View, bundle: Bundle?)
}
