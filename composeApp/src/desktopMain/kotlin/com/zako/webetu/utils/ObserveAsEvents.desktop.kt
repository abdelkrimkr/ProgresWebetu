package com.zako.webetu.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.swing.Swing
import kotlinx.coroutines.withContext


@Composable
actual fun <T> ObserveAsEvents(
    flow: Flow<T>,
    key1: Any?,
    key2: Any?,
    onEvent: (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner.lifecycle, key1, key2, flow) {
            withContext(Dispatchers.Swing.immediate) {
                flow.collect(onEvent)
            }
    }
}