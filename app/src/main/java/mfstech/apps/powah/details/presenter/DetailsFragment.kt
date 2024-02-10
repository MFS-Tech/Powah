package mfstech.apps.powah.details.presenter

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import mfstech.apps.powah.R
import mfstech.apps.powah.common.CommonFragment
import mfstech.apps.powah.common.database.model.Device
import mfstech.apps.powah.common.sse.SensorEvent
import mfstech.apps.powah.confirmation.ConfirmationDialogState
import mfstech.apps.powah.databinding.FragmentDetailsBinding
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
        parametersOf(this, args.device)
    }

    private val adapter = DetailsAdapter()

    override fun onInitComponents() {
        binding.eventsList.adapter = adapter
        binding.backButton.setOnClickListener { viewModel.onBackClicked() }
        binding.editButton.setOnClickListener { viewModel.onEditClicked() }
        binding.deleteButton.setOnClickListener { viewModel.onDeleteClicked() }
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

    override fun bindConnectionFailure() {
        binding.eventsList.visibility = View.GONE
        binding.warning.visibility = View.VISIBLE
        binding.loadingContainer.visibility = View.GONE
    }

    override fun bindNewEvent(sensorEvent: SensorEvent) {
        binding.eventsList.visibility = View.VISIBLE
        binding.warning.visibility = View.GONE
        binding.loadingContainer.visibility = View.GONE
        adapter.setEvent(sensorEvent)
    }

    override fun showDeleteConfirmationDialog() {
        findNavController().navigate(
            DetailsFragmentDirections.openConfirmationDialog(
                data = ConfirmationDialogState(
                    title = getString(R.string.delete_confirmation_title),
                    description = getString(R.string.delete_confirmation_description),
                    positiveButton = ConfirmationDialogState.Button(getString(R.string.delete_confirmation_positive_button)) {
                        viewModel.onDeleteConfirm()
                    },
                    negativeButton = ConfirmationDialogState.Button(getString(R.string.delete_confirmation_negative_button))
                )
            )
        )
    }

    override fun openEditDevice(device: Device) {
        navigate(DetailsFragmentDirections.inputDevice(device))
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