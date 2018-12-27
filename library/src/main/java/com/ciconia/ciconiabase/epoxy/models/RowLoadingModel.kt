package com.ciconia.ciconiabase.epoxy.models

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.ciconia.ciconiabase.epoxy.KotlinEpoxyHolder
import com.ciconia.library.R2


@EpoxyModelClass(layout = R2.layout.row_loading_view_model)
abstract class RowLoadingModel : EpoxyModelWithHolder<RowLoadingModel.Holder>() {

    class Holder : KotlinEpoxyHolder()
}


