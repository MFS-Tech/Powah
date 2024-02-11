package com.mfstech.powah.home.presenter

import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.mfstech.powah.common.CommonFragment
import com.mfstech.powah.common.database.model.Device
import com.mfstech.powah.databinding.FragmentHomeBinding
import com.mfstech.powah.input.presenter.InputFragmentDirections
import com.mfstech.powah.options.DeviceOptionsDialogFragmentDirections
import com.mfstech.powah.options.DeviceOptionsDialogState
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeFragment : CommonFragment<FragmentHomeBinding, HomeViewModel>(), HomeContract.View {

    override val viewModel: HomeViewModel by inject {
        parametersOf(this)
    }

    override val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val deviceListAdapter = HomeDeviceListAdapter(
        viewModel::onDeviceClicked,
        viewModel::onDeviceLongClicked
    )

    override fun onInitComponents() {
        binding.deviceList.adapter = deviceListAdapter
        binding.addDeviceButton.setOnClickListener { viewModel.onAddNewDeviceClicked() }
        binding.searchInput.doOnTextChanged { text, _, _, _ -> viewModel.onSearchChange(text.toString()) }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun openAddNewDevice() {
        navigate(HomeFragmentDirections.inputDevice())
    }

    override fun openDevice(device: Device) {
        navigate(HomeFragmentDirections.openDetails(device.id))
    }

    override fun openEditDevice(device: Device) {
        navigate(HomeFragmentDirections.inputDevice(device.id))
    }

    override fun bindDevices(devices: List<Device>) {
        deviceListAdapter.devices = devices
    }

    override fun showDialogMenu(device: Device) {
        findNavController().navigate(
            InputFragmentDirections.openConfirmationDialog(
                data = DeviceOptionsDialogState(
                    title = device.name.ifBlank { device.route },
                    positiveButton = DeviceOptionsDialogState.Button("Editar", onClick = {
                        navigate(DeviceOptionsDialogFragmentDirections.inputDevice(device.id))
                    }),
                    negativeButton = DeviceOptionsDialogState.Button("Excluir", onLongClick = {
                        viewModel.onDeviceOptionsDeleteClicked(device)
                    })
                )
            )
        )
    }
}

