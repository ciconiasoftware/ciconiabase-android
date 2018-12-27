package com.ciconia.android.base.epoxy.models

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.ciconia.android.base.epoxy.KotlinEpoxyHolder
import com.ciconia.android.base.R2


@EpoxyModelClass(layout = R2.layout.row_loading_view_model)
abstract class RowLoadingModel : EpoxyModelWithHolder<RowLoadingModel.Holder>() {

    class Holder : KotlinEpoxyHolder()
}


