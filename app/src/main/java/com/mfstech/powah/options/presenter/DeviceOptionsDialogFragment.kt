package com.mfstech.powah.options.presenter

import androidx.navigation.fragment.navArgs
import com.mfstech.powah.common.business.database.model.Device
import com.mfstech.powah.common.presenter.dialogfragment.CommonFragmentDialog
import com.mfstech.powah.common.util.ext.isVisibleWhen
import com.mfstech.powah.databinding.FragmentDeviceOptionsBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DeviceOptionsDialogFragment :
    CommonFragmentDialog<FragmentDeviceOptionsBinding, DeviceOptionsViewModel>(),
    DeviceOptionsContract.View {

    private val args: DeviceOptionsDialogFragmentArgs by navArgs()

    override val binding: FragmentDeviceOptionsBinding by lazy {
        FragmentDeviceOptionsBinding.inflate(layoutInflater)
    }

    override val viewModel: DeviceOptionsViewModel by inject {
        parametersOf(this, args.id)
    }

    override fun onInitComponents() {
        binding.editButton.setOnClickListener { viewModel.onEditClicked() }
        binding.deleteButton.setOnLongClickListener {
            viewModel.onDeleteClicked()
            true
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    override fun bindDevice(device: Device) {
        binding.deviceRoute.isVisibleWhen { device.name.isNotBlank() }
        binding.deviceName.text = device.name.ifBlank { device.route }
        binding.deviceRoute.text = device.route
    }

    override fun openEditDevice(device: Device) {
        navigate(DeviceOptionsDialogFragmentDirections.inputDevice(device.id))
    }
}

