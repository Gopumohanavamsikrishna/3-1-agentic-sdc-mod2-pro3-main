package project.handson3.viewmodel

import androidx.lifecycle.*import kotlinx.coroutines.launch
import project.handson3.model.PromptResponse
import project.handson3.repository.PromptRepository

class PromptViewModel : ViewModel() {

    private val repository = PromptRepository()
    private val _responses = MutableLiveData<List<PromptResponse>>()
    val responses: LiveData<List<PromptResponse>> get() = _responses

    fun generate(task: String) {
        viewModelScope.launch {
            try {
                // Wrap the single result in a list so it matches LiveData<List<PromptResponse>>
                val result = repository.generate(task)
                _responses.value = listOf(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
