package com.justadev.guessnumber.presentation.ui.results

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.justadev.guessnumber.core.BaseFragment
import com.justadev.guessnumber.databinding.FragmentResultBinding

class ResultFragment : BaseFragment<FragmentResultBinding>(FragmentResultBinding::inflate) {

    private val viewModel: ResultViewModel by lazy {
        val resultText = ResultFragmentArgs.fromBundle(requireArguments()).resultText
        val factory = ResultViewModel.Factory(resultText)
        ViewModelProvider(this, factory).get(ResultViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTryAgain.setOnClickListener {
            this@ResultFragment.findNavController().navigate(ResultFragmentDirections.actionResultFragmentToGuessNumberFragment())
        }

        viewModel.observeResult(viewLifecycleOwner) {result ->
            binding.textResults.text = result
        }


    }


}