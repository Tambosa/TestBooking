package com.example.feature_booking

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

const val REG = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}\$"
var PATTERN: Pattern = Pattern.compile(REG)
fun CharSequence.isPhoneNumber(): Boolean = PATTERN.matcher(this).find()

fun CharSequence.isValidEmail() =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
