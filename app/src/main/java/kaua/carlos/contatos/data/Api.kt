package kaua.carlos.contatos.data

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface Api {
    // Autenticação
    @POST("usuarios/registrar")
    suspend fun registrar(@Body dados: RegistroEnvio): Response<MensagemResposta>

    @POST("usuarios/login")
    suspend fun login(@Body credenciais: Usuario): Response<LoginResponse>

    // Contatos (Cabeçalhos de Autorização removidos, pois o ApiClient já faz isso)
    @GET("contatos")
    suspend fun listarContatos(): Response<List<Contato>>

    @POST("contatos")
    suspend fun criarContato(@Body contato: Contato): Response<Contato>

    @PUT("contatos/{id}")
    suspend fun atualizarContato(
        @Path("id") id: String,
        @Body contato: Contato
    ): Response<Contato>

    @DELETE("contatos/{id}")
    suspend fun deletarContato(
        @Path("id") id: String
    ): Response<MensagemResponse>

    // Foto (GridFS) - O nome "foto" deve ser exato ao do multipart no Node.js
    @Multipart
    @POST("contatos/{id}/foto")
    suspend fun uploadFoto(
        @Path("id") id: String,
        @Part foto: MultipartBody.Part
    ): Response<MensagemResponse>
}