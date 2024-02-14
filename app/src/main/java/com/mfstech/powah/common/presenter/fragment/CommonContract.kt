package com.mfstech.powah.common.presenter.fragment

interface CommonContract {

    interface View {
        fun goBack()
    }

    interface ViewModel<V : View> {
        val view: V
    }
}