package com.mfstech.powah.input.presenter

import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mfstech.powah.R
import com.mfstech.powah.common.CommonFragment
import com.mfstech.powah.common.util.InputWarning
import com.mfstech.powah.confirmation.ConfirmationDialogState
import com.mfstech.powah.confirmation.ConfirmationDialogState.Button
import com.mfstech.powah.databinding.FragmentInputBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class InputFragment :
    CommonFragment<FragmentInputBinding, InputViewModel>(),
    InputContract.View {

    private val args: InputFragmentArgs by navArgs()

    override val binding: FragmentInputBinding by lazy {
        FragmentInputBinding.inflate(layoutInflater)
    }

    override val viewModel: InputViewModel by inject {
        parametersOf(this, args.device)
    }

    override fun onInitComponents() {
        with(binding) {
            backButton.setOnClickListener { viewModel.onBackClicked() }
            deleteButton.setOnClickListener { viewModel.onDeleteClicked() }
            routeInput.doOnTextChanged { text, _, _, _ -> viewModel.validateRoute(text.toString()) }
            saveButton.setOnClickListener {
                viewModel.onSaveClicked(
                    nameInput.text.toString(),
                    routeInput.text.toString()
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun showRouteWarning(warning: InputWarning) {
        binding.routeInputLayout.error = getString(warning.res)
    }

    override fun clearRouteWarning() {
        binding.routeInputLayout.error = null
    }

    override fun bindName(name: String) {
        binding.nameInputLayout.editText?.setText(name)
    }

    override fun bindRoute(route: String) {
        binding.routeInputLayout.editText?.setText(route)
    }

    override fun showDeleteConfirmation() {
        findNavController().navigate(
            InputFragmentDirections.openConfirmationDialog(
                data = ConfirmationDialogState(
                    title = getString(R.string.delete_confirmation_title),
                    description = getString(R.string.delete_confirmation_description),
                    positiveButton = Button(getString(R.string.delete_confirmation_positive_button)) {
                        viewModel.onDeleteConfirm()
                    },
                    negativeButton = Button(getString(R.string.delete_confirmation_negative_button))
                )
            )
        )
    }
}