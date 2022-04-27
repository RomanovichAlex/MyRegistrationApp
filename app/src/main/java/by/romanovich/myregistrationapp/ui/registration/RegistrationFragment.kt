package by.romanovich.myregistrationapp.ui.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import by.romanovich.myregistrationapp.databinding.FragmentRegistrationBinding
import by.romanovich.myregistrationapp.ui.base.BaseFragment


class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.createAccountButton.setOnClickListener {
            Toast.makeText(requireContext(), "createAccount", Toast.LENGTH_SHORT).show()
        }
    }
}