package com.justadev.guessnumber.presentation.ui.guessNumber

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justadev.guessnumber.core.ResourceManager
import com.justadev.guessnumber.domain.CompareGuessNumberUseCase
import com.justadev.guessnumber.domain.GetRandomNumberUseCase
import com.justadev.guessnumber.domain.ValidateNumberUseCase
import com.justadev.guessnumber.domain.entities.RandomNumber
import com.justadev.guessnumber.domain.entities.ResultDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuessNumberViewModel @Inject constructor(
    private val getRandomNumberUseCase: GetRandomNumberUseCase,
    private val validateNumberUseCase: ValidateNumberUseCase,
    private val compareGuessNumberUseCase: CompareGuessNumberUseCase
) : ViewModel() {

    private val countAttempts = MutableLiveData<Int>()
    private val randomNumber = MutableLiveData<ResultDomain>()
    private val guessText = MutableLiveData<Pair<Boolean, String>?>()

    init {
        countAttempts.value = 5
    }

    fun reset() {
        countAttempts.value = 5
        guessText.value = null
    }

    fun getRandomNumber() = viewModelScope.launch {
        randomNumber.value = ResultDomain.Loading
        val numberProcess = async(Dispatchers.IO) {
            getRandomNumberUseCase()
        }
        randomNumber.value = numberProcess.await()
    }

    fun observeRandomNumber(lifecycleOwner: LifecycleOwner, observer: Observer<ResultDomain>) {
        randomNumber.observe(lifecycleOwner, observer)
    }

    fun compareGuessNumber(guessNumber: String) {
        when(val result = randomNumber.value) {
            is ResultDomain.Success -> {
                val resultValue = compareGuessNumberUseCase.compare(guessNumber, result.data.number)
                guessText.value = resultValue
                if (!resultValue.first){
                    decrementAttempt()
                }
            }
            else -> {
                guessText.value = Pair(false, "Виникла помилка!")
            }
        }
    }

    fun observeGuessText(lifecycleOwner: LifecycleOwner, observer: Observer<Pair<Boolean, String>?>){
        guessText.observe(lifecycleOwner, observer)
    }


    fun observeAttempts(lifecycleOwner: LifecycleOwner, observer: Observer<Int>) {
        countAttempts.observe(lifecycleOwner, observer)
    }

    fun decrementAttempt() {
        val count = countAttempts.value
        val newCount = count?.minus(1)
        countAttempts.value = newCount!!
    }

    fun validateNumber(number: String?): Boolean = validateNumberUseCase(number)


}