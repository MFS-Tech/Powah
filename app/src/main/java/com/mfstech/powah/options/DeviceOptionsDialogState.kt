package com.mfstech.powah.options

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DeviceOptionsDialogState(
    val title: String,
    val description: String? = null,
    val positiveButton: Button,
    val negativeButton: Button? = null
) : Parcelable {

    @Parcelize
    data class Button(
        val value: String,
        val onClick: (() -> Unit)? = null,
        val onLongClick: (() -> Boolean)? = null
    ) : Parcelable
}