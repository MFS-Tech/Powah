package com.mfstech.powah.details.presenter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mfstech.powah.common.sse.SensorEvent
import com.mfstech.powah.databinding.ItemDeviceSensorStateBinding

class SensorEventViewHolder(
    private val binding: ItemDeviceSensorStateBinding
) : ViewHolder(binding.root) {

    fun bind(data: SensorEvent) {
        data.sensor?.let {
            binding.sensorLabel.text = binding.root.context.getString(it.res)
        }
        binding.sensorValue.text = data.state
    }
}