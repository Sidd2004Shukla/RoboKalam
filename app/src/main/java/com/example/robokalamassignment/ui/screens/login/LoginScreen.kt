package com.example.robokalamassignment.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.robokalamassignment.R
import com.example.robokalamassignment.RobokalamApp
import com.example.robokalamassignment.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    app: RobokalamApp
) {
    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModel.Factory(app.userPreferences)
    )
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val isEmailError by viewModel.isEmailError.collectAsState()
    val isPasswordError by viewModel.isPasswordError.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        
        Image(
            painter = painterResource(id = R.drawable.robokalam_logo),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
        
        Text(
            text = stringResource(id = R.string.login_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = viewModel::onEmailChange,
            label = { Text(stringResource(id = R.string.email_hint)) },
            singleLine = true,
            isError = isEmailError,
            supportingText = {
                if (isEmailError) {
                    Text(
                        text = stringResource(id = R.string.email_error),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = viewModel::onPasswordChange,
            label = { Text(stringResource(id = R.string.password_hint)) },
            singleLine = true,
            isError = isPasswordError,
            supportingText = {
                if (isPasswordError) {
                    Text(
                        text = stringResource(id = R.string.password_error),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.validateAndSaveCredentials {
                    navController.navigate(Routes.DASHBOARD) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = stringResource(id = R.string.continue_button),
                fontSize = 18.sp
            )
        }
    }
} 