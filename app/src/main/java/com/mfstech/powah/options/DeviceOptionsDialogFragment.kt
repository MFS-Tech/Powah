package com.mfstech.powah.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mfstech.powah.common.ext.isVisibleWhen
import com.mfstech.powah.common.ext.orFalse
import com.mfstech.powah.databinding.FragmentDeviceOptionsBinding

class DeviceOptionsDialogFragment : BottomSheetDialogFragment() {

    private val args: DeviceOptionsDialogFragmentArgs by navArgs()

    private val binding: FragmentDeviceOptionsBinding by lazy {
        FragmentDeviceOptionsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindTitle()
        bindDescription()
        bindPositiveButton()
        bindNegativeButton()
    }

    private fun bindTitle() {
        binding.title.text = args.data.title
    }

    private fun bindDescription() {
        binding.description.isVisibleWhen { args.data.description != null }
        binding.description.text = args.data.description
    }

    private fun bindPositiveButton() {
        binding.positiveButton.text = args.data.positiveButton.value
        if (args.data.positiveButton.onLongClick != null) {
            binding.positiveButton.setOnLongClickListener {
                args.data.positiveButton.onLongClick?.invoke().orFalse().also {
                    if (it) findNavController().popBackStack()
                }
            }
        }
        binding.positiveButton.setOnClickListener {
            args.data.positiveButton.onClick?.invoke()
        }
    }

    private fun bindNegativeButton() {
        binding.negativeButton.isVisibleWhen { args.data.negativeButton != null }
        binding.negativeButton.text = args.data.negativeButton?.value
        if (args.data.negativeButton?.onLongClick != null) {
            binding.negativeButton.setOnLongClickListener {
                args.data.negativeButton?.onLongClick?.invoke().orFalse().also {
                    if (it) findNavController().popBackStack()
                }
            }
        }
        binding.negativeButton.setOnClickListener {
            args.data.negativeButton?.onClick?.invoke()
            findNavController().popBackStack()
        }
    }
}

