package com.zako.webetu.auth.login.presentation

import androidx.compose.runtime.Composable
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zako.webetu.R
import com.zako.webetu.ui.theme.WebetuTheme

@Composable
fun LoginScreenRoot(modifier: Modifier = Modifier) {
    LoginScreen()
    // delete this
}

@Composable
private fun LoginScreen(
    modifier: Modifier = Modifier
) {

            NetworkSquaresEffect(
                squaresCount = 9 ,
                squaresColor = MaterialTheme.colorScheme.background.copy(alpha = 0.05f) ,
                linesWight = 3f
            ){
                Text(
                    modifier = Modifier.padding(start = 16.dp , top =  64.dp ),
                    text = "Enterprise Resource",
                    style = MaterialTheme.typography.headlineLarge,
                    color = contentColorFor(MaterialTheme.colorScheme.primary),
                    fontWeight = FontWeight.ExtraBold ,
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp ),
                    text = "Planning",
                    style = MaterialTheme.typography.headlineLarge,
                    color = contentColorFor(MaterialTheme.colorScheme.primary),
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp  ),
                    text = "Student Portal",
                    style = MaterialTheme.typography.bodyLarge,
                    color = contentColorFor(MaterialTheme.colorScheme.primary),
                    fontWeight = FontWeight.Bold
                )
            }


}


@Composable
private fun NetworkSquaresEffect(
    modifier: Modifier = Modifier,
    squaresCount : Int ,
    squaresColor: Color,
    linesWight: Float ,
    content: @Composable ColumnScope.() -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(200.dp ),
            painter = painterResource(R.drawable.light_login_effect) ,
            contentDescription = null
        )
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            val squaresSize = canvasWidth / squaresCount


            val horizontalSquaresCount = canvasWidth / squaresSize
            val verticalSquaresCount = canvasHeight / horizontalSquaresCount


            // Draw horizontal lines
            for (i in 0..horizontalSquaresCount.toInt()) {
                val x = i * squaresSize

                drawLine(
                    color = squaresColor,
                    start = Offset(x, 0f),
                    end = Offset(x, canvasHeight),
                    strokeWidth = linesWight
                )
            }


            //Draw vertical lines
            for (i in 0..verticalSquaresCount.toInt()) {
                val y = i * squaresSize

                drawLine(
                    color = squaresColor,
                    start = Offset(0f, y),
                    end = Offset(canvasWidth, y),
                    strokeWidth = linesWight
                )
            }


        }
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            content()
        }
    }
}
