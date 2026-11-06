package kaua.carlos.contatos.data

import com.google.gson.annotations.SerializedName

// Classes de envio e resposta da Etapa 2
data class RegistroEnvio(
    val nome: String,
    val email: String,
    val senha: String
)

data class MensagemResposta(val mensagem: String?)

// Respostas genéricas da API
data class MensagemResponse(val mensagem: String)
data class LoginResponse(val token: String, val usuarioId: String)

// Modelos de Usuário e Contato
data class Usuario(
    val nome: String,
    val email: String,
    val senha: String? = null
)

data class Contato(
    @SerializedName("_id") val id: String? = null,
    val nome: String,
    val telefone: String,
    val email: String,
    val fotoId: String? = null // Referência ao GridFS
)