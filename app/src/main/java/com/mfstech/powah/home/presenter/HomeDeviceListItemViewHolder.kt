package com.mfstech.powah.home.presenter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mfstech.powah.common.business.database.model.Device
import com.mfstech.powah.common.util.ext.asGone
import com.mfstech.powah.common.util.ext.asVisible
import com.mfstech.powah.databinding.ItemDeviceBinding

class HomeDeviceListItemViewHolder(
    private val onClick: (device: Device) -> Unit,
    private val onLongClick: (device: Device) -> Unit,
    private val binding: ItemDeviceBinding,
) : ViewHolder(binding.root) {

    fun bind(device: Device) = with(binding) {
        this.root.setOnClickListener { onClick(device) }
        this.root.setOnLongClickListener {
            onLongClick(device)
            true
        }

        if (device.name.isBlank()) {
            deviceName.text = device.route
            deviceRoute.asGone()
        } else {
            deviceName.text = device.name
            deviceRoute.text = device.route
            deviceRoute.asVisible()
        }
    }
}