package com.zako.webetu.utils

import org.jetbrains.compose.resources.StringResource


sealed class BaseString {
    class DynamicString(val value: String) : BaseString()
    class ResString(val value: StringResource) : BaseString()
}

//@Composable
//expect fun BaseString.asString(): String {
//    val context = LocalContext.current
//    return when (this) {
//        is BaseString.DynamicString -> {
//            this.value
//        }
//        is BaseString.ResString -> context.getString(this.value)
//    }
//}
//
//fun BaseString.asString(context: Context): String {
//    return when (this) {
//        is BaseString.DynamicString -> {
//            this.value
//        }
//        is BaseString.ResString -> context.getString(this.value)
//    }
//}
