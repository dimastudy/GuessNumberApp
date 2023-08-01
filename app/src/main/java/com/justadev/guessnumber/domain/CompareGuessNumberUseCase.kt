package com.justadev.guessnumber.domain

import com.justadev.guessnumber.R
import com.justadev.guessnumber.core.ResourceManager
import javax.inject.Inject

class CompareGuessNumberUseCase @Inject constructor (
    private val resourceManager: ResourceManager
) {

    fun compare(guessNumber: String ,number: Int,) : Pair<Boolean, String> {
        val guessNumberInt = guessNumber.toInt()
        return if (guessNumberInt == number){
            Pair(true, resourceManager.getString(R.string.win_message))
        } else if (guessNumberInt < number) {
            Pair(false, resourceManager.getString(R.string.number_less))
        } else {
            Pair(false, resourceManager.getString(R.string.number_bigger))
        }
    }

}