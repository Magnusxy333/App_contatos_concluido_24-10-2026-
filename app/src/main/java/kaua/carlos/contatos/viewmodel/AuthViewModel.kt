package kaua.carlos.contatos.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kaua.carlos.contatos.data.RegistroEnvio
import kaua.carlos.contatos.data.Repositorio
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    // Estados para os campos de texto
    var nome by mutableStateOf("")
    var email by mutableStateOf("")
    var senha by mutableStateOf("")

    // Estados para feedback visual
    var carregando by mutableStateOf(false)
    var mensagem by mutableStateOf<String?>(null)

    fun registrar() {
        if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
            mensagem = "Preencha todos os campos"
            return
        }

        viewModelScope.launch {
            carregando = true
            mensagem = null
            try {
                val envio = RegistroEnvio(nome, email, senha)
                val resposta = Repositorio.registrar(envio)
                mensagem = resposta.mensagem ?: "Usuário criado com sucesso!"

                // Limpa os campos após o sucesso
                nome = ""
                email = ""
                senha = ""
            } catch (e: Exception) {
                // Captura o erro lançado pelo Repositorio
                mensagem = e.message
            } finally {
                carregando = false
            }
        }
    }
}