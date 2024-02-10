package mfstech.apps.powah.home.presenter

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mfstech.apps.powah.common.CommonViewModel
import mfstech.apps.powah.common.database.model.Device
import mfstech.apps.powah.home.business.HomeRepository
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    override val view: HomeContract.View,
    private val repository: HomeRepository,
    private val dispatcher: CoroutineContext
) : CommonViewModel(), HomeContract.ViewModel {

    override fun onStart() {
        viewModelScope.launch(dispatcher) {
            val devices = repository.findDevices()
            view.bindDevices(devices)
        }
    }

    override fun onSearchChange(search: String) {
        viewModelScope.launch(dispatcher) {
            val devices = repository.findDevices(search)
            view.bindDevices(devices)
        }
    }

    override fun onAddNewDeviceClicked() {
        view.openAddNewDevice()
    }

    override fun onDeviceClicked(device: Device) {
        view.openDevice(device)
    }

    override fun onDeviceLongClicked(device: Device) {
        view.openEditDevice(device)
    }
}