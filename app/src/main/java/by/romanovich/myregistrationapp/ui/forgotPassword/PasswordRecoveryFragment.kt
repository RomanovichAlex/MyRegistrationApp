package by.romanovich.myregistrationapp.ui.forgotPassword

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import by.romanovich.myregistrationapp.R
import by.romanovich.myregistrationapp.app
import by.romanovich.myregistrationapp.databinding.FragmentPasswordRecoveryBinding
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.ui.AppState
import by.romanovich.myregistrationapp.ui.base.BaseFragment


class PasswordRecoveryFragment :
    BaseFragment<FragmentPasswordRecoveryBinding>(FragmentPasswordRecoveryBinding::inflate) {

    private var viewModel: PasswordRecoveryContract.ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.app?.let { PasswordRecoveryViewModel(it.passwordRecoveryUsecase) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickBySubscription()
    }

    private fun initClickBySubscription() {
        viewModel?.getLiveData()?.subscribe(Handler(Looper.getMainLooper())) { state ->
            renderData(state)
        }
        binding.sendInstructionsAccountButton.setOnClickListener {
            viewModel?.findAccount(
                binding.sendInstructionsAccountButton.text.toString()
            )
            Toast.makeText(
                requireContext(),
                getString(R.string.sendInstructions),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun renderData(result: AppState) {
        binding.cardViewContainer.isVisible = false
        when (result) {
            is AppState.Loading -> {
                binding.cardViewContainer.isVisible = true
            }
            is AppState.Success -> {
                forgetPasswordData(result.userProfile)
            }
            is AppState.Error -> {
                showError(result.error)
            }
        }
    }


    private fun showError(error: Exception) {
        binding.cardViewContainer.setBackgroundColor(Color.RED)
        Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
    }

    private fun forgetPasswordData(account: UserProfile) {
        Toast.makeText(requireContext(), getString(R.string.succes), Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel?.getLiveData()?.unsubscribeAll()
    }
}