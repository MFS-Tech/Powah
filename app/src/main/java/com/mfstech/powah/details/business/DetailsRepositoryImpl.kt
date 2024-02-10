package com.mfstech.powah.details.business

import android.util.Log
import com.mfstech.powah.common.database.DeviceDao
import com.mfstech.powah.common.database.model.Device
import com.mfstech.powah.common.sse.eventSourceListener
import com.mfstech.powah.details.model.SensorEventListenerState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.OkHttpClient
import okhttp3.Request

class DetailsRepositoryImpl(
    private val request: Request,
    private val client: OkHttpClient,
    private val dao: DeviceDao
) : DetailsRepository {

    override fun getDeviceSensors(): Flow<SensorEventListenerState> = callbackFlow {
        val listener = eventSourceListener(
            client = client,
            request = request,
            onEvent = { event ->
                Log.i(TAG, "onEvent ->\n$event")
                trySend(SensorEventListenerState.SensorEventListenerReceived(event))
            },
            onFailure = { error ->
                Log.e(TAG, "onFailure ->\n", error)
                trySend(SensorEventListenerState.ConnectionFailed(error))
            }
        )

        runCatching { client.newCall(request).execute() }
            .onFailure { send(SensorEventListenerState.ConnectionFailed(it)) }
        send(SensorEventListenerState.Connecting)
        awaitClose { listener.cancel() }
    }

    override suspend fun deleteDevice(device: Device) = dao.delete(device)

    companion object {
        const val TAG = "DetailsRepo"
    }
}