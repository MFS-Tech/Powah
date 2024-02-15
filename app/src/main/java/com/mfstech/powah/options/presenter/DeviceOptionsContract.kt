package com.mfstech.powah.options.presenter

import com.mfstech.powah.common.business.database.model.Device
import com.mfstech.powah.common.presenter.dialogfragment.CommonFragmentDialogContract

interface DeviceOptionsContract {

    interface View : CommonFragmentDialogContract.View {
        fun bindDevice(device: Device)
        fun openEditDevice(device: Device)
    }

    interface ViewModel : CommonFragmentDialogContract.ViewModel<View> {
        fun onStart()
        fun onDeleteClicked()
        fun onEditClicked()
        fun onDestroy()
    }
}