package com.mfstech.powah.confirmation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mfstech.powah.databinding.FragmentConfirmationBinding

class ConfirmationDialogFragment : BottomSheetDialogFragment() {

    private val args: ConfirmationDialogFragmentArgs by navArgs()

    private val binding: FragmentConfirmationBinding by lazy {
        FragmentConfirmationBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title.text = args.data.title
        binding.description.text = args.data.description
        binding.positiveButton.text = args.data.positiveButton.value
        binding.negativeButton.text = args.data.negativeButton?.value
        binding.positiveButton.setOnClickListener {
            args.data.positiveButton.onClick?.invoke()
            findNavController().popBackStack()
        }
        binding.negativeButton.setOnClickListener {
            args.data.negativeButton?.onClick?.invoke()
            findNavController().popBackStack()
        }
    }
}

