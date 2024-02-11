package com.mfstech.powah.details.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfstech.powah.common.sse.SensorEvent
import com.mfstech.powah.databinding.ItemDeviceSensorStateBinding

class DetailsAdapter : RecyclerView.Adapter<SensorEventViewHolder>() {

    private val list = arrayListOf<SensorEvent>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorEventViewHolder {
        return SensorEventViewHolder(
            ItemDeviceSensorStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SensorEventViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun clear() {
        val lastSize = this.list.size
        this.list.clear()
        notifyItemRangeRemoved(0, lastSize)
    }

    fun setEvent(sensorEvent: SensorEvent) {
        if (sensorEvent.sensor == null) return

        val old = list.find { it.sensor == sensorEvent.sensor }
        if (old != null) {
            val index = list.indexOf(old)
            list[index] = sensorEvent
            notifyItemChanged(index)
        } else {
            val lastIndex = list.size - 1
            list.add(sensorEvent)
            notifyItemInserted(lastIndex + 1)
        }
    }
}