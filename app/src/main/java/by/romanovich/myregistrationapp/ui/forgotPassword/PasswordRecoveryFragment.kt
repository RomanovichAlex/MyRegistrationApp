package by.romanovich.myregistrationapp.ui.forgotPassword

import android.os.Bundle
import android.view.View
import android.widget.Toast
import by.romanovich.myregistrationapp.R
import by.romanovich.myregistrationapp.databinding.FragmentPasswordRecoveryBinding
import by.romanovich.myregistrationapp.ui.base.BaseFragment


class PasswordRecoveryFragment :
    BaseFragment<FragmentPasswordRecoveryBinding>(FragmentPasswordRecoveryBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.sendInstructionsAccountButton.setOnClickListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.sendInstructions),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}