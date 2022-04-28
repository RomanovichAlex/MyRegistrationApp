package by.romanovich.myregistrationapp.ui.registration

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import by.romanovich.myregistrationapp.app
import by.romanovich.myregistrationapp.databinding.FragmentRegistrationBinding
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.ui.AppState
import by.romanovich.myregistrationapp.ui.base.BaseFragment


class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    private var viewModel: RegistrationContract.ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.app?.let { RegistrationViewModel(it.registrationUsecase) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickBySubscription()


        binding.createAccountButton.setOnClickListener {
            Toast.makeText(requireContext(), "createAccount", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initClickBySubscription() {
        viewModel?.getLiveData()?.subscribe(Handler(Looper.getMainLooper())) { state ->
            renderData(state)
        }

        binding.createAccountButton.setOnClickListener {
            viewModel?.onRegistration(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString(),
                binding.emailEditText.text.toString(),
            )
        }
    }


    private fun renderData(result: AppState) {
        binding.cardViewContainer.isVisible = false
        when (result) {
            is AppState.Loading -> {
                binding.cardViewContainer.isVisible = true
            }
            is AppState.Success -> {
                loadAccountData(result.userProfile)
            }
            is AppState.Error -> {
                showError(result.error)
            }
        }
    }

    private fun loadAccountData(account: UserProfile) {

        binding.root.setBackgroundColor(Color.GREEN)
        Toast.makeText(requireContext(), "createAccount", Toast.LENGTH_SHORT).show()
    }

    private fun showError(error: Exception) {
        binding.cardViewContainer.setBackgroundColor(Color.RED)
        Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
    }
}
