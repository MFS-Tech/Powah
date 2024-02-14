package com.mfstech.powah.common.presenter.dialogfragment

interface CommonFragmentDialogContract {
    interface View {
        fun close()
    }

    interface ViewModel<V : View> {
        val view: V
    }
}