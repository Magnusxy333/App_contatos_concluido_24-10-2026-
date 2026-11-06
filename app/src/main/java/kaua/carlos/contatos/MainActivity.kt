package kaua.carlos.contatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kaua.carlos.contatos.ui.theme.ContatosTheme
import kaua.carlos.contatos.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContatosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaRegistro(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TelaRegistro(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Criar Conta", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = viewModel.nome,
            onValueChange = { viewModel.nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.senha,
            onValueChange = { viewModel.senha = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.registrar() },
            modifier = Modifier.fillMaxWidth(),
            enabled = !viewModel.carregando
        ) {
            Text(if (viewModel.carregando) "Registrando..." else "Registrar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Exibe a mensagem de sucesso ou erro
        viewModel.mensagem?.let { msg ->
            Text(text = msg, color = MaterialTheme.colorScheme.primary)
        }
    }
}