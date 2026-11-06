package kaua.carlos.contatos.data

import androidx.compose.runtime.*

object Sessao {
    var token by mutableStateOf<String?>(null)
    var usuarioId by mutableStateOf<String?>(null) // Necessário para associar os contactos ao utilizador

    // Função para chamar quando o utilizador fizer logout
    fun limparSessao() {
        token = null
        usuarioId = null
    }
}