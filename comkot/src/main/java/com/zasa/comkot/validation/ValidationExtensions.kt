package com.zasa.comkot.validation

import java.util.regex.Pattern

private const val PHONE_ONLY_DIGITS_PATTERN = "[0-9+]+"

fun String.isValidPhoneNumber(): Boolean =
    Pattern.compile(PHONE_ONLY_DIGITS_PATTERN).matcher(this).matches() && length >= 7 && length <= 14

fun String.isValidEmail() =
    android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
