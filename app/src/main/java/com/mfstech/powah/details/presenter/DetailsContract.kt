package com.mfstech.powah.details.presenter

import com.mfstech.powah.common.business.database.model.Device
import com.mfstech.powah.common.business.sse.SensorEvent
import com.mfstech.powah.common.presenter.fragment.CommonContract

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