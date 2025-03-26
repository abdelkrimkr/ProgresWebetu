package com.zako.webetu.auth.login.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zako.webetu.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot() {
    val loginScreenViewModel = koinViewModel<LoginScreenViewModel>()
    val state by loginScreenViewModel.state.collectAsState()

    LoginScreen(
        state = state,
        onRegistrationNumberChange = { registrationNumber ->
            loginScreenViewModel.action(
                LoginScreenActions.RegistrationNumberChange(
                    registrationNumber
                )
            )
        },
        onPasswordChange = { password ->
            loginScreenViewModel.action(LoginScreenActions.PasswordChange(password))
        },
        onShowPasswordClicked = {
            loginScreenViewModel.action(LoginScreenActions.ShowPasswordClicked)
        },
        onHidePasswordClicked = {
            loginScreenViewModel.action(LoginScreenActions.HidePasswordClicked)
        },
        onLoginClicked = {
            loginScreenViewModel.action(LoginScreenActions.LoginClicked)
        }
    )
}

@Composable
private fun LoginScreen(
    modifier: Modifier = Modifier,
    state: LoginScreenState,
    onRegistrationNumberChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onShowPasswordClicked: () -> Unit,
    onHidePasswordClicked: () -> Unit,
    onLoginClicked: () -> Unit,
) {

    NetworkSquaresEffect(
        squaresCount = 9,
        squaresColor = MaterialTheme.colorScheme.background.copy(alpha = 0.05f),
        linesWight = 3f
    ) {
        AnimatedVisibility(
            visible = state.isLoading
        ) {
            LinearProgressIndicator()
        }
        HeadLine()
        Column(
            modifier = modifier
                .padding(top = 33.dp)
                .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Image(
                modifier = Modifier
                    .width(265.dp)
                    .height(100.dp),
                painter = painterResource(R.drawable.progress_banner),
                contentDescription = stringResource(R.string.progress_brand_banner),
            )
            Spacer(modifier = Modifier.height(68.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .padding(bottom = 2.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                text = stringResource(R.string.registration_number)
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .heightIn(min = 48.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                isError = state.error.isNotBlank(),
                supportingText = {
                    if (state.error.isNotBlank()) {
                        Text(
                            color = MaterialTheme.colorScheme.error,
                            text = state.error
                        )
                    }
                },
                value = state.registrationNumber,
                onValueChange = onRegistrationNumberChange,
                textStyle = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .padding(bottom = 2.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                text = stringResource(R.string.password)
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .heightIn(min = 48.dp)
                    .fillMaxWidth(),
                isError = state.error.isNotBlank(),
                supportingText = {
                    if (state.error.isNotBlank()) {
                        Text(
                            color = MaterialTheme.colorScheme.error,
                            text = state.error
                        )
                    }
                },
                shape = MaterialTheme.shapes.medium,
                value = state.password,
                onValueChange = onPasswordChange,
                textStyle = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else
                    PasswordVisualTransformation(),
                suffix = {
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                if (state.isPasswordVisible) onHidePasswordClicked() else onShowPasswordClicked()
                            }
                            .size(24.dp),
                        painter = painterResource(if (state.isPasswordVisible) R.drawable.eye_open else R.drawable.eye_off),
                        contentDescription = stringResource(R.string.password_visibility_icon),
                    )
                }

            )

            Spacer(
                modifier = Modifier.height(29.dp)
            )

            Button(
                enabled = state.registrationNumber.isNotBlank() and state.password.isNotBlank(),
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .heightIn(min = 48.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                onClick = onLoginClicked
            ) {
                Text(
                    text = stringResource(R.string.login),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            Spacer(
                modifier = Modifier.height(21.dp)
            )

            Text(
                text = stringResource(R.string.ministry_of_higher_education_and_scientific_research),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = stringResource(R.string.copyright_2025),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }


}


@Composable
private fun HeadLine() {
    Text(
        modifier = Modifier.padding(start = 16.dp, top = 48.dp),
        text = stringResource(R.string.enterprise_resource),
        style = MaterialTheme.typography.headlineLarge,
        color = contentColorFor(MaterialTheme.colorScheme.primary),
        fontWeight = FontWeight.ExtraBold,
    )
    Text(
        modifier = Modifier.padding(start = 16.dp),
        text = stringResource(R.string.planning),
        style = MaterialTheme.typography.headlineLarge,
        color = contentColorFor(MaterialTheme.colorScheme.primary),
        fontWeight = FontWeight.ExtraBold
    )
    Text(
        modifier = Modifier.padding(start = 16.dp),
        text = stringResource(R.string.student_portal),
        style = MaterialTheme.typography.bodyLarge,
        color = contentColorFor(MaterialTheme.colorScheme.primary),
        fontWeight = FontWeight.Bold
    )
}


@Composable
private fun NetworkSquaresEffect(
    modifier: Modifier = Modifier,
    squaresCount: Int,
    squaresColor: Color,
    linesWight: Float,
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
                .size(200.dp),
            painter = painterResource(R.drawable.light_login_effect),
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
