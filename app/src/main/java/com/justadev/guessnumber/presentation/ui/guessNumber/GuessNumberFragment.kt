package com.justadev.guessnumber.presentation.ui.guessNumber

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.justadev.guessnumber.R
import com.justadev.guessnumber.core.BaseFragment
import com.justadev.guessnumber.core.animateAlpha
import com.justadev.guessnumber.core.hideKeyboard
import com.justadev.guessnumber.databinding.FragmentGuessNumberBinding
import com.justadev.guessnumber.domain.entities.ResultDomain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuessNumberFragment :
    BaseFragment<FragmentGuessNumberBinding>(FragmentGuessNumberBinding::inflate) {

    private val viewModel: GuessNumberViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRandomNumber()
        viewModel.observeRandomNumber(viewLifecycleOwner) { result ->
            when (result) {
                is ResultDomain.Success -> {
                    binding.apply {
                        progressBar.isVisible = false
                        animateAlpha(questionImage, 0f, 1f, false, lifecycleScope){}
                        numberText.text = result.data.number.toString()
                        etNumber.isEnabled = true
                        binding.btnSubmit.isVisible = true
                        binding.btnRetry.isVisible = false
                    }
                }

                is ResultDomain.Error -> {
                    Snackbar.make(requireView(), result.message, Snackbar.LENGTH_LONG).show()
                    binding.etNumber.isEnabled = false
                    binding.progressBar.isVisible = false
                    binding.btnSubmit.isVisible = false
                    binding.btnRetry.isVisible = true
                }

                ResultDomain.Loading -> {
                    binding.apply {
                        progressBar.isVisible = true
                        questionImage.alpha = 0f
                        binding.etNumber.isEnabled = false
                        binding.btnSubmit.isVisible = false
                        binding.btnRetry.isVisible = false
                    }
                }
            }
        }

        viewModel.observeGuessText(viewLifecycleOwner) { pair ->
            if (pair != null){
                if (pair.first) {
                    binding.btnSubmit.isEnabled = false
                    endGame(pair.second)
                    binding.textGuessed.text = ""
                } else {
                    binding.textGuessed.text = pair.second
                }
            }
        }


        viewModel.observeAttempts(viewLifecycleOwner) { attempts ->
            if (attempts <= 0) {
                binding.btnSubmit.isEnabled = false
                endGame(getString(R.string.lose_text))
            }
            binding.attemptsText.text = getString(R.string.attempts_text, attempts.toString())
        }


        binding.apply {
            btnSubmit.isEnabled = false
            etNumber.addTextChangedListener {
                if (viewModel.validateNumber(it?.toString())) {
                    textInputLayout.isHelperTextEnabled = false
                    btnSubmit.isEnabled = true
                } else {
                    textInputLayout.isHelperTextEnabled = true
                    textInputLayout.helperText = getString(R.string.helper_text)
                    btnSubmit.isEnabled = false
                }
            }
            btnSubmit.setOnClickListener {
                hideKeyboard()
                viewModel.compareGuessNumber(etNumber.text.toString())
            }
            btnRetry.setOnClickListener {
                viewModel.getRandomNumber()
            }
        }

    }

    fun endGame(text: String) {
        animateAlpha(binding.questionImage, 1f, 0f, false, lifecycleScope){
            animateAlpha(binding.numberText, 0f, 1f, true, lifecycleScope){
                this@GuessNumberFragment.findNavController().navigate(
                    GuessNumberFragmentDirections.actionGuessNumberFragmentToResultFragment(text)
                )
                viewModel.reset()
            }
        }
    }

}
