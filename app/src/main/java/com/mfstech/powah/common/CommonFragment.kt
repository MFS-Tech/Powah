package com.mfstech.powah.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class CommonFragment<V : ViewBinding, VM : CommonViewModel> : Fragment(),
    CommonContract.View {

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

    override fun goBack() {
        findNavController().popBackStack()
    }
}