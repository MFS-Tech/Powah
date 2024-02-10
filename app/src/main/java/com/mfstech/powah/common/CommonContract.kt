package com.mfstech.powah.common

interface CommonContract {

    interface View {
        fun goBack()
    }

    interface ViewModel<V : View> {
        val view: V
    }
}