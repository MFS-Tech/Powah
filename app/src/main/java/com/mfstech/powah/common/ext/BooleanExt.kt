package com.mfstech.powah.common.ext

fun Boolean?.orFalse(): Boolean {
    if (this == null) {
        return false
    }

    return this
}