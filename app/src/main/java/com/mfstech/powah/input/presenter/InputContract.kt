package com.mfstech.powah.input.presenter

import com.mfstech.powah.common.CommonContract
import com.mfstech.powah.common.util.InputWarning

interface InputContract {

    interface View : CommonContract.View {
        fun bindName(name: String)
        fun bindRoute(route: String)
        fun showRouteWarning(warning: InputWarning)
        fun clearRouteWarning()
    }

    interface ViewModel : CommonContract.ViewModel<View> {
        fun onStart()
        fun validateRoute(route: String): Boolean
        fun onSaveClicked(name: String, route: String)
        fun onBackClicked()
    }
}
