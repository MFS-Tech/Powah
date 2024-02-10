package mfstech.apps.powah.common.util

import androidx.annotation.StringRes
import mfstech.apps.powah.R

private interface Predicate {
    fun verify(input: String): Boolean
}

enum class InputWarning(
    @StringRes val res: Int
) : Predicate {
    IS_BLANK(R.string.input_warning_is_blank) {
        override fun verify(input: String): Boolean = input.isBlank()
    },
    INVALID_IPV4(
        R.string.input_warning_invalid_ipv4
    ) {
        override fun verify(input: String): Boolean {
            return !input.matches("^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.){3}(25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\$".toRegex())
        }
    };
}

fun List<InputWarning>.validate(
    input: String
): InputWarning? = this.firstOrNull { it.verify(input) }