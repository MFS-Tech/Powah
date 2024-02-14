package com.mfstech.powah.options.presenter

import androidx.lifecycle.viewModelScope
import com.mfstech.powah.common.business.database.model.Device
import com.mfstech.powah.common.presenter.CommonViewModel
import com.mfstech.powah.options.business.DeviceOptionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DeviceOptionsViewModel(
    override val view: DeviceOptionsContract.View,
    private val id: Int,
    private val repository: DeviceOptionsRepository,
    private val dispatcher: CoroutineContext,
) : CommonViewModel(), DeviceOptionsContract.ViewModel {


    private var device: Device? = null

    override fun onStart() {
        viewModelScope.launch(dispatcher) {
            repository.getDevice(id)
                .flowOn(Dispatchers.Main)
                .onEach {
                    view.bindDevice(it)
                    device = it
                }
                .single()
        }
    }

    override fun onDeleteClicked() {
        viewModelScope.launch { device?.let { repository.delete(it) } }
        view.close()
    }

    override fun onEditClicked() {
        device?.let(view::openEditDevice)
    }
}