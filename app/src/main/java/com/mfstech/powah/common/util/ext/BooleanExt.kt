package com.mfstech.powah.common.util.ext

fun Boolean?.orFalse(): Boolean {
    if (this == null) {
        return false
    }

    return this
}