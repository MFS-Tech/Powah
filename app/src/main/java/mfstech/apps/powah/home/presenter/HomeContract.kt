package mfstech.apps.powah.home.presenter

import mfstech.apps.powah.common.CommonContract
import mfstech.apps.powah.common.database.model.Device

interface HomeContract {

    interface View : CommonContract.View {
        fun openAddNewDevice()
        fun openDevice(device: Device)
        fun openEditDevice(device: Device)
        fun bindDevices(devices: List<Device>)
    }

    interface ViewModel : CommonContract.ViewModel<View> {
        fun onStart()
        fun onSearchChange(search: String)
        fun onAddNewDeviceClicked()
        fun onDeviceClicked(device: Device)
        fun onDeviceLongClicked(device: Device)
    }
}