package mfstech.apps.powah.home.presenter

import androidx.core.widget.doOnTextChanged
import mfstech.apps.powah.common.CommonFragment
import mfstech.apps.powah.common.database.model.Device
import mfstech.apps.powah.databinding.FragmentHomeBinding
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
        navigate(HomeFragmentDirections.openDetails(device))
    }

    override fun openEditDevice(device: Device) {
        navigate(HomeFragmentDirections.inputDevice(device))
    }

    override fun bindDevices(devices: List<Device>) {
        deviceListAdapter.devices = devices
    }


}

