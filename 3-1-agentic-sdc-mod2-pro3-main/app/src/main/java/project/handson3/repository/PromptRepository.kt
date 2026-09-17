package project.handson3.repository

import project.handson3.model.PromptRequest
import project.handson3.model.PromptResponse
import project.handson3.network.ApiClient

class PromptRepository {
    // This function must be named 'generate' to match your ViewModel call
    suspend fun generate(task: String): PromptResponse {
        return ApiClient.api.generate(PromptRequest(task))
    }
}