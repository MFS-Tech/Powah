package com.mfstech.powah.common.business.sse

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources

private typealias onEvent = (sensorEvent: SensorEvent) -> Unit
private typealias onFailure = (error: Throwable?) -> Unit

fun eventSourceListener(
    client: OkHttpClient,
    request: Request,
    onEvent: onEvent? = null,
    onFailure: onFailure? = null,
): EventSource = EventSources.createFactory(client)
    .newEventSource(request, object : EventSourceListener() {
        override fun onEvent(
            eventSource: EventSource,
            id: String?,
            type: String?,
            data: String
        ) {
            super.onEvent(eventSource, id, type, data)
            if (type == "state") {
                onEvent?.invoke(Gson().fromJson(data, SensorEvent::class.java))
            }
        }

        override fun onFailure(
            eventSource: EventSource,
            t: Throwable?,
            response: Response?
        ) {
            super.onFailure(eventSource, t, response)
            onFailure?.invoke(t)
        }
    })