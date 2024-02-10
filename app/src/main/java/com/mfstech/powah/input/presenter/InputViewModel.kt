package com.mfstech.powah.input.presenter

import androidx.lifecycle.viewModelScope
import com.mfstech.powah.common.CommonViewModel
import com.mfstech.powah.common.database.model.Device
import com.mfstech.powah.common.util.InputWarning
import com.mfstech.powah.common.util.validate
import com.mfstech.powah.input.business.InputRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class InputViewModel(
    override val view: InputContract.View,
    private val id: Int,
    private val repository: InputRepository,
    private val dispatcher: CoroutineContext
) : CommonViewModel(), InputContract.ViewModel {

    override fun onStart() {
        viewModelScope.launch(dispatcher) {
            repository.get(id)
                .onEach { device ->
                    view.bindName(device.name)
                    view.bindRoute(device.route)
                }
                .flowOn(Dispatchers.Main)
                .collect()
        }
    }

    override fun validateRoute(route: String): Boolean {
        val warning = listOf(
            InputWarning.IS_BLANK,
            InputWarning.INVALID_IPV4
        ).validate(route)

        return if (warning != null) {
            view.showRouteWarning(warning)
            false
        } else {
            view.clearRouteWarning()
            true
        }
    }

    override fun onSaveClicked(name: String, route: String) {
        val predicates = listOf(
            validateRoute(route)
        )

        if (predicates.all { it }) {
            viewModelScope.launch(dispatcher) {
                repository.save(Device(id = id, name = name, route = route))
            }
            view.goBack()
        }
    }

    override fun onBackClicked() {
        view.goBack()
    }
}