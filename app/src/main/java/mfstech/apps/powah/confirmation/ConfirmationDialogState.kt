package mfstech.apps.powah.confirmation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConfirmationDialogState(
    val title: String,
    val description: String,
    val positiveButton: Button,
    val negativeButton: Button?
) : Parcelable {

    @Parcelize
    data class Button(
        val value: String,
        val onClick: (() -> Unit)? = null
    ) : Parcelable
}