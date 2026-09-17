package project.handson3.network

import project.handson3.model.PromptRequest
import project.handson3.model.PromptResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("generate")
    suspend fun generate(@Body request: PromptRequest): PromptResponse
}