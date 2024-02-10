package com.mfstech.powah.input.presenter

import androidx.lifecycle.viewModelScope
import com.mfstech.powah.common.CommonViewModel
import com.mfstech.powah.common.database.model.Device
import com.mfstech.powah.common.util.InputWarning
import com.mfstech.powah.common.util.validate
import com.mfstech.powah.input.business.InputRepository
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class InputViewModel(
    override val view: InputContract.View,
    private val device: Device?,
    private val repository: InputRepository,
    private val dispatcher: CoroutineContext
) : CommonViewModel(), InputContract.ViewModel {

    override fun onStart() {
        device?.let {
            view.bindName(it.name)
            view.bindRoute(it.route)
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
                repository.save(Device(id = device?.id ?: 0, name = name, route = route))
            }
            view.goBack()
        }
    }

    override fun onDeleteClicked() {
        view.showDeleteConfirmation()
    }

    override fun onDeleteConfirm() {
        device?.let {
            viewModelScope.launch(dispatcher) { repository.delete(it) }
            view.goBack()
        }
    }

    override fun onBackClicked() {
        view.goBack()
    }
}