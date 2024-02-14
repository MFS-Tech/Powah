package com.mfstech.powah.common.business.sse

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.mfstech.powah.R

data class SensorEvent(
    @SerializedName("id") val id: String,
    @SerializedName("value") val value: String?,
    @SerializedName("state") val state: String?
) {
    val sensor: Sensor?
        get() = Sensor.entries.find { it.value == id }

    enum class Sensor(val value: String, @StringRes val res: Int) {
        MAC_ADDRESS("text_sensor-mac_address", R.string.sensor_label_mac_address),
        CONNECTED_SSID("text_sensor-connected_ssid", R.string.sensor_label_connected_ssid),
        POWER("sensor-power", R.string.sensor_label_power),
        ENERGY("sensor-energy", R.string.sensor_label_energy),
        TOTAL_ENERGY("sensor-total_energy", R.string.sensor_label_total_energy),
        TOTAL_DAILY_ENERGY("sensor-total_daily_energy", R.string.sensor_label_total_daily_energy),
        CURRENT("sensor-current", R.string.sensor_label_current),
        VOLTAGE("sensor-voltage", R.string.sensor_label_voltage),
        UP_TIME("sensor-uptime_sensor", R.string.sensor_label_up_time),
        WIFI_SIGNAL("sensor-wifi_signal", R.string.sensor_label_wifi_signal),
    }
}