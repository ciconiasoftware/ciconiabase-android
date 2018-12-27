package com.ciconia.ciconiabase.epoxy.models

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.ciconia.ciconiabase.epoxy.KotlinEpoxyHolder
import com.ciconia.library.R
import com.ciconia.library.R2


@EpoxyModelClass(layout = R2.layout.model_settings_item)
abstract class SettingsItemModel : EpoxyModelWithHolder<SettingsItemModel.Holder>() {

    @EpoxyAttribute @StringRes var text: Int? = null
    @EpoxyAttribute @DrawableRes var icon: Int? = null
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash) var listener: View.OnClickListener? = null


    override fun bind(holder: Holder) {
        super.bind(holder)
        text?.let {
            holder.text.setText(it)
        }
        icon?.let {
            holder.icon.setImageDrawable(holder.icon.context.getDrawable(it))
        }

        listener?.let {
            holder.text.setOnClickListener(listener)
        }
    }

    class Holder : KotlinEpoxyHolder() {
        val layout by bind<LinearLayout>(R.id.settings_item_layout)
        val text by bind<TextView>(R.id.text)
        val icon by bind<ImageView>(R.id.icon)

    }
}
