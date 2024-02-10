package mfstech.apps.powah.home.presenter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import mfstech.apps.powah.common.database.model.Device
import mfstech.apps.powah.databinding.ItemDeviceBinding

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
            deviceRoute.visibility = View.GONE
        } else {
            deviceName.text = device.name
            deviceRoute.text = device.route
            deviceRoute.visibility = View.VISIBLE
        }
    }
}