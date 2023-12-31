package navigationcomponentturtorialcom.example.quizapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import navigationcomponentturtorialcom.example.quizapp.model.QuestionModel
import navigationcomponentturtorialcom.example.quizapp.repository.QuestionRepository


class QuestionViewModel(private val repository: QuestionRepository) : ViewModel() {
    private var _questionMutableLiveData = MutableLiveData<List<QuestionModel>>()
    val questionMutableLiveData: LiveData<List<QuestionModel>> get() = _questionMutableLiveData

    private val _currentQuestionIndex = MutableLiveData<Int>()
    val currentQuestionIndex: LiveData<Int> get() = _currentQuestionIndex

    init {
        _currentQuestionIndex.value = 0 // Set the initial quiz index to start from the first quiz
        getQuestions()
    }

    fun getQuestions() {
        repository.getQuestions { questionList ->
            _questionMutableLiveData.postValue(questionList)
        }
    }

    fun setCurrentQuestionIndex(index: Int) {
        _currentQuestionIndex.value = index
    }

    fun getCurrentQuestion(): QuestionModel {
        // Trả về câu hỏi hiện tại từ danh sách
        return _questionMutableLiveData.value!![_currentQuestionIndex.value!!]
    }

}