package com.mfstech.powah.home.presenter

import androidx.lifecycle.viewModelScope
import com.mfstech.powah.common.CommonViewModel
import com.mfstech.powah.common.database.model.Device
import com.mfstech.powah.home.business.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.future.future
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    override val view: HomeContract.View,
    private val repository: HomeRepository,
    private val dispatcher: CoroutineContext
) : CommonViewModel(), HomeContract.ViewModel {

    override fun onStart() {
        viewModelScope.launch(dispatcher) {
            repository.findDevices()
                .onEach(view::bindDevices)
                .flowOn(Dispatchers.Main)
                .collect()
        }
    }

    override fun onSearchChange(search: String) {
        viewModelScope.launch(dispatcher) {
            repository.findDevices(search)
                .onEach(view::bindDevices)
                .flowOn(Dispatchers.Main)
                .collect()
        }
    }

    override fun onAddNewDeviceClicked() {
        view.openAddNewDevice()
    }

    override fun onDeviceClicked(device: Device) {
        view.openDevice(device)
    }

    override fun onDeviceLongClicked(device: Device) {
        view.showDialogMenu(device)
    }

    override fun onDeviceOptionsEditClicked(device: Device) {
        view.openEditDevice(device)
    }

    override fun onDeviceOptionsDeleteClicked(device: Device): Boolean {
        return viewModelScope.future(dispatcher) { repository.deleteDevice(device) }.get()
    }
}