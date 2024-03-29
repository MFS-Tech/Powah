package com.mfstech.powah.details.presenter

import androidx.navigation.fragment.navArgs
import com.mfstech.powah.common.business.database.model.Device
import com.mfstech.powah.common.business.sse.SensorEvent
import com.mfstech.powah.common.presenter.fragment.CommonFragment
import com.mfstech.powah.common.util.ext.asGone
import com.mfstech.powah.common.util.ext.asVisible
import com.mfstech.powah.databinding.FragmentDetailsBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DetailsFragment :
    CommonFragment<FragmentDetailsBinding, DetailsViewModel>(),
    DetailsContract.View {

    private val args: DetailsFragmentArgs by navArgs()

    override val binding: FragmentDetailsBinding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    override val viewModel: DetailsViewModel by inject {
        parametersOf(this, args.id)
    }

    private val adapter = DetailsAdapter()

    override fun onInitComponents() {
        binding.eventsList.adapter = adapter
        binding.backButton.setOnClickListener { viewModel.onBackClicked() }
        binding.editButton.setOnClickListener { viewModel.onEditClicked() }
    }

    override fun bindDevice(device: Device) {
        if (device.name.isBlank()) {
            binding.deviceRoute.text = ""
            binding.deviceName.text = device.route
        } else {
            binding.deviceName.text = device.name
            binding.deviceRoute.text = device.route
        }
    }

    override fun bindDeviceConnecting() {
        binding.eventsList.asGone()
        binding.warning.asGone()
        binding.loadingContainer.asVisible()
    }

    override fun bindConnectionFailure(error: Throwable?) {
        binding.eventsList.asGone()
        binding.loadingContainer.asGone()
        binding.warning.asVisible()
        binding.warning.text = error?.message
    }

    override fun bindNewEvent(sensorEvent: SensorEvent) {
        binding.warning.asGone()
        binding.loadingContainer.asGone()
        binding.eventsList.asVisible()
        adapter.setEvent(sensorEvent)
    }

    override fun openEditDevice(id: Int) {
        navigate(DetailsFragmentDirections.inputDevice(id))
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
        adapter.clear()
    }
}