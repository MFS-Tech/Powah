package mfstech.apps.powah.details.presenter

import mfstech.apps.powah.common.CommonContract
import mfstech.apps.powah.common.database.model.Device
import mfstech.apps.powah.common.sse.SensorEvent

interface DetailsContract {

    interface View : CommonContract.View {
        fun bindDevice(device: Device)
        fun showDeleteConfirmationDialog()
        fun openEditDevice(device: Device)
        fun bindDeviceConnecting()
        fun bindConnectionFailure()
        fun bindNewEvent(sensorEvent: SensorEvent)
    }

    interface ViewModel : CommonContract.ViewModel<View> {
        fun onResume()
        fun onPause()
        fun onBackClicked()
        fun onEditClicked()
        fun onDeleteClicked()
        fun onDeleteConfirm()
    }
}