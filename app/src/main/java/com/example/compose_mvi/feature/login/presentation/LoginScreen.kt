package com.example.compose_mvi.feature.login.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose_mvi.core.utils.CommonTextField
import com.example.compose_mvi.core.utils.singleClickable

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavController
) {
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current
    val pwFocusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when(effect) {
                is LoginContract.Effect.NavigationToMenu -> {
                    navController.navigate("menu") {
                        popUpTo("login") {inclusive = true}
                    }
                }

                is LoginContract.Effect.ShowToast -> {
                    Toast.makeText(context, effect.msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // -------------------------
        // 아이디 입력
        // -------------------------

        CommonTextField(
            value = state.value.id,
            onValueChange = {
                viewModel.sendIntent(
                    LoginContract.Intent.OnIdChanged(it)
                )
            },
            label = "아이디",
            imeAction = ImeAction.Next,
            nextFocusRequester = pwFocusRequester,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // -------------------------
        // 비밀번호 입력
        // -------------------------
        CommonTextField(
            value = state.value.pw,
            onValueChange = {
                viewModel.sendIntent(
                    LoginContract.Intent.OnPwChanged(it)
                )
            },
            label = "비밀번호",
            visualTransformation = PasswordVisualTransformation(),
            imeAction = ImeAction.Done,
            onDone = {
                viewModel.sendIntent(
                    LoginContract.Intent.Login(
                        state.value.id,
                        state.value.pw
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(pwFocusRequester)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {},
            modifier = Modifier.singleClickable{
                viewModel.sendIntent(
                    LoginContract.Intent.Login(state.value.id, state.value.pw)
                )
            }
        ) {
            Text("로그인")
        }

        if (state.value.isLoading) {
            CircularProgressIndicator()
        }
    }
}