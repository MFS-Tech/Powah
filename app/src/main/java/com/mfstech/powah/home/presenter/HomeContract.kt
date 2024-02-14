package com.mfstech.powah.home.presenter

import com.mfstech.powah.common.business.database.model.Device
import com.mfstech.powah.common.presenter.fragment.CommonContract

interface HomeContract {

    interface View : CommonContract.View {
        fun openAddNewDevice()
        fun openDevice(device: Device)
        fun openEditDevice(device: Device)
        fun bindDevices(devices: List<Device>)
        fun showDialogMenu(device: Device)

    }

    interface ViewModel : CommonContract.ViewModel<View> {
        fun onStart()
        fun onSearchChange(search: String)
        fun onAddNewDeviceClicked()
        fun onDeviceClicked(device: Device)
        fun onDeviceLongClicked(device: Device)
        fun onDeviceOptionsEditClicked(device: Device)
        fun onDeviceOptionsDeleteClicked(device: Device): Boolean
        fun onDestroy()
    }
}