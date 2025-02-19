package com.example.navegacao.ui.telas

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.navegacao.modelo.repositorio.UsuarioDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val usuarioDAO: UsuarioDAO = UsuarioDAO()

@Composable
fun TelaLogin(modifier: Modifier = Modifier, onSignInClick: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var login by remember {mutableStateOf("")}
    var senha by remember {mutableStateOf("")}
    var mensagemErro by remember { mutableStateOf<String?>(null) }

    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxSize()) {
            OutlinedTextField(value = login, onValueChange = {login = it},
                label = {Text("Login")}
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(value = senha, onValueChange = {senha = it},
                placeholder = {Text("Senha")},
                visualTransformation = PasswordVisualTransformation(),
                )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                usuarioDAO.buscarPorNome(login, callback  = {
                    usuario ->
                    if (usuario != null && usuario.senha == senha) {
                        onSignInClick()
                    } else {
                        mensagemErro = "Login ou senha inválidos!"
                    }
                })
            }) {
                Text("Entrar")
            }
        mensagemErro?.let {
            LaunchedEffect(it) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                mensagemErro = null
            }
        }
    }
}