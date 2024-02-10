package mfstech.apps.powah.input.presenter

import mfstech.apps.powah.common.CommonContract
import mfstech.apps.powah.common.util.InputWarning

interface InputContract {

    interface View : CommonContract.View {
        fun bindName(name: String)
        fun bindRoute(route: String)
        fun showRouteWarning(warning: InputWarning)
        fun showDeleteConfirmation()
        fun clearRouteWarning()
    }

    interface ViewModel : CommonContract.ViewModel<View> {
        fun onStart()
        fun validateRoute(route: String): Boolean
        fun onSaveClicked(name: String, route: String)
        fun onDeleteClicked()
        fun onDeleteConfirm()
        fun onBackClicked()
    }
}
