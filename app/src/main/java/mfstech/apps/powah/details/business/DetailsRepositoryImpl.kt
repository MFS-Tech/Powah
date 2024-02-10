package mfstech.apps.powah.details.business

import android.util.Log
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import mfstech.apps.powah.common.database.DeviceDao
import mfstech.apps.powah.common.database.model.Device
import mfstech.apps.powah.common.sse.eventSourceListener
import mfstech.apps.powah.details.model.SensorEventListenerState
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