package com.mfstech.powah.details.presenter

import com.mfstech.powah.common.CommonContract
import com.mfstech.powah.common.database.model.Device
import com.mfstech.powah.common.sse.SensorEvent

interface DetailsContract {

    interface View : CommonContract.View {
        fun bindDevice(device: Device)
        fun openEditDevice(id: Int)
        fun bindDeviceConnecting()
        fun bindConnectionFailure(error: Throwable?)
        fun bindNewEvent(sensorEvent: SensorEvent)
    }

    interface ViewModel : CommonContract.ViewModel<View> {
        fun onResume()
        fun onPause()
        fun onBackClicked()
        fun onEditClicked()
    }
}