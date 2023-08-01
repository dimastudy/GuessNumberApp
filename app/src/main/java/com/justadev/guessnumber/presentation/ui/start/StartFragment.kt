package com.justadev.guessnumber.presentation.ui.start

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.justadev.guessnumber.core.BaseFragment
import com.justadev.guessnumber.databinding.FragmentStartScreenBinding

class StartFragment :
    BaseFragment<FragmentStartScreenBinding>(FragmentStartScreenBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStartGame.setOnClickListener {
            this@StartFragment.findNavController()
                .navigate(StartFragmentDirections.actionStartFragmentToGuessNumberFragment())
        }

    }


}