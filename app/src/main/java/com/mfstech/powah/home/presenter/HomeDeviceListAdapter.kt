package com.mfstech.powah.home.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfstech.powah.common.database.model.Device
import com.mfstech.powah.databinding.ItemDeviceBinding

class HomeDeviceListAdapter(
    private val onDeviceClicked: (device: Device) -> Unit,
    private val onDeviceLongClicked: (device: Device) -> Unit,
) : RecyclerView.Adapter<HomeDeviceListItemViewHolder>() {

    var devices: List<Device> = emptyList()
        set(value) {
            field = value
            notifyItemRangeChanged(0, value.size)
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeDeviceListItemViewHolder {
        return HomeDeviceListItemViewHolder(
            onDeviceClicked,
            onDeviceLongClicked,
            ItemDeviceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun getItemCount(): Int = devices.size

    override fun onBindViewHolder(holder: HomeDeviceListItemViewHolder, position: Int) {
        holder.bind(devices[position])
    }
}