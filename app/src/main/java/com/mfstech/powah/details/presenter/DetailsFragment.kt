package com.mfstech.powah.details.presenter

import android.view.View
import androidx.navigation.fragment.navArgs
import com.mfstech.powah.common.CommonFragment
import com.mfstech.powah.common.database.model.Device
import com.mfstech.powah.common.sse.SensorEvent
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
        binding.deviceName.text = device.name
        binding.deviceRoute.text = device.route
    }

    override fun bindDeviceConnecting() {
        binding.eventsList.visibility = View.GONE
        binding.warning.visibility = View.GONE
        binding.loadingContainer.visibility = View.VISIBLE
    }

    override fun bindConnectionFailure(error: Throwable?) {
        binding.eventsList.visibility = View.GONE
        binding.loadingContainer.visibility = View.GONE
        binding.warning.visibility = View.VISIBLE
        binding.warning.text = error?.message
    }

    override fun bindNewEvent(sensorEvent: SensorEvent) {
        binding.warning.visibility = View.GONE
        binding.loadingContainer.visibility = View.GONE
        binding.eventsList.visibility = View.VISIBLE
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
    }
}