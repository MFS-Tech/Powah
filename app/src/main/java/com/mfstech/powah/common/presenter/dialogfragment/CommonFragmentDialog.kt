package com.mfstech.powah.common.presenter.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mfstech.powah.common.presenter.CommonViewModel

abstract class CommonFragmentDialog<V : ViewBinding, VM : CommonViewModel> :
    BottomSheetDialogFragment(), CommonFragmentDialogContract.View {

    abstract val binding: V

    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitComponents()
    }

    abstract fun onInitComponents()

    open fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    override fun close() {
        dismiss()
    }
}