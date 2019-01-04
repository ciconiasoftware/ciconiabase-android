package com.ciconia.android.base.epoxy.models

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.ciconia.android.base.epoxy.KotlinEpoxyHolder
import com.ciconia.android.base.R
import com.ciconia.android.base.R2


@EpoxyModelClass(layout = R2.layout.header_view_model)
abstract class HeaderViewModel : EpoxyModelWithHolder<HeaderViewModel.Holder>() {

    @EpoxyAttribute lateinit var title: String
    @EpoxyAttribute var separatorVisible: Boolean = false
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash) var titleLayoutParams: LinearLayout.LayoutParams? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.title.text = title
        if (separatorVisible)
            holder.separator.visibility = View.VISIBLE
        else
            holder.separator.visibility = View.GONE

        titleLayoutParams?.let {
            holder.title.layoutParams = it
        }
    }

    class Holder : KotlinEpoxyHolder() {
        val title by bind<TextView>(R.id.title)
        val separator by bind<LinearLayout>(R.id.separator)

    }
}
