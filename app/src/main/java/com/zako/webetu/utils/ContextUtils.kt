package com.zako.webetu.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.zako.webetu.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


fun Context.getActivity(): Activity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

fun Context.openLink(
    link : String
){
    try {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        this.startActivity(webIntent)
    } catch (e: Exception) {
       this.shortToast(this.getString(R.string.error_opening_the_link))
    }
}

fun Context.shortToast(message: String) {
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_SHORT
    ).show()
}
suspend fun Context.shortIoToast(message: String) {
    withContext(Dispatchers.Main.immediate) {
        Toast.makeText(
            this@shortIoToast,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}

fun Context.shareIntent(message : String){
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, message)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    this.startActivity(shareIntent)
}