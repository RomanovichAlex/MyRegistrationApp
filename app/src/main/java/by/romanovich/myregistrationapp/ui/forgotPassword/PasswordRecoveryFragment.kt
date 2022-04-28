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
import by.romanovich.myregistrationapp.ui.base.BaseFragment
import by.romanovich.myregistrationapp.ui.state.AppState
import by.romanovich.myregistrationapp.ui.state.ViewState


class PasswordRecoveryFragment :
    BaseFragment<FragmentPasswordRecoveryBinding>(FragmentPasswordRecoveryBinding::inflate) {
    private val viewSaveState = "ViewSaveState"
    private var viewState: ViewState = ViewState.INIT
    private var viewModel: PasswordRecoveryContract.ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.app?.let { PasswordRecoveryViewModel(it.passwordRecoveryUsecase) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickBySubscription()
        savedInstanceState?.let {
            viewState = ViewState.fromInt(
                it.getInt(viewSaveState, 0)
            )
        }
        restoreStateUi()
    }

    private fun initClickBySubscription() {
        viewModel?.getLiveData()?.subscribe(Handler(Looper.getMainLooper())) { state ->
            renderData(state)
        }
        binding.sendInstructionsAccountButton.setOnClickListener {
            viewModel?.findAccount(
                binding.emailEditText.text.toString()
            )
        }
    }

    private fun renderData(result: AppState) {
        when (result) {
            is AppState.Loading -> {
                binding.passwordRecoveryCardViewContainer.isVisible = true
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
        viewState = ViewState.ERROR
        binding.passwordRecoveryCardViewContainer.setBackgroundColor(Color.RED)
        Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
    }

    private fun forgetPasswordData(account: UserProfile) {
        viewState = ViewState.IS_SUCCESS
        binding.passwordRecoveryCardViewContainer.setBackgroundColor(Color.GREEN)
        Toast.makeText(
            requireContext(),
            getString(R.string.sendInstructions),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun restoreStateUi() {
        when (viewState) {
            ViewState.INIT -> {}
            ViewState.ERROR -> {
                binding.passwordRecoveryCardViewContainer.setBackgroundColor(Color.RED)
            }
            ViewState.IS_SUCCESS -> {
                binding.passwordRecoveryCardViewContainer.setBackgroundColor(Color.GREEN)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(viewSaveState, viewState.value)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel?.getLiveData()?.unsubscribeAll()
    }
}