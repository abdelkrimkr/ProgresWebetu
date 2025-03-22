package com.zako.webetu.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

sealed class BaseString {
    class DynamicString(val value: String) : BaseString()
    class ResString(val value: Int) : BaseString()
}


@Composable
fun BaseString.asString(modifier: Modifier = Modifier) : String {
    val context = LocalContext.current
    return when(this) {
        is BaseString.DynamicString -> {
            this.value
        }
        is BaseString.ResString -> context.getString(this.value)
    }
}

fun BaseString.asString(context : Context ) : String {
    return when(this) {
        is BaseString.DynamicString -> {
            this.value
        }
        is BaseString.ResString -> context.getString(this.value)
    }
}



