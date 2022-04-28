package by.romanovich.myregistrationapp.ui.registration

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import by.romanovich.myregistrationapp.R
import by.romanovich.myregistrationapp.app
import by.romanovich.myregistrationapp.databinding.FragmentRegistrationBinding
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.ui.base.BaseFragment
import by.romanovich.myregistrationapp.ui.state.AppState
import by.romanovich.myregistrationapp.ui.state.ViewState


class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    private val viewSaveState = "ViewSaveState"
    private var viewState: ViewState = ViewState.INIT
    private var viewModel: RegistrationContract.ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.app?.let { RegistrationViewModel(it.registrationUsecase) }
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

        binding.createAccountButton.setOnClickListener {
            viewModel?.onRegistration(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString(),
                binding.emailEditText.text.toString(),
            )
        }
    }


    private fun renderData(result: AppState) {
        when (result) {
            is AppState.Loading -> {
                binding.registrationCardViewContainer.isVisible = true
            }
            is AppState.Success -> {
                binding.registrationCardViewContainer.isVisible = false
                loadAccountData(result.userProfile)
            }
            is AppState.Error -> {
                showError(result.error)
            }
        }
    }

    private fun loadAccountData(account: UserProfile) {
        viewState = ViewState.IS_SUCCESS
        binding.registrationCardViewContainer.setBackgroundColor(Color.GREEN)
        Toast.makeText(requireContext(), getString(R.string.acc_create), Toast.LENGTH_SHORT).show()
    }

    private fun restoreStateUi() {
        when (viewState) {
            ViewState.INIT -> {}
            ViewState.ERROR -> {
                binding.registrationCardViewContainer.setBackgroundColor(Color.RED)
                Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
            }
            ViewState.IS_SUCCESS -> {
                binding.registrationCardViewContainer.setBackgroundColor(Color.GREEN)
            }
        }
    }

    private fun showError(error: Exception) {
        binding.registrationCardViewContainer.setBackgroundColor(Color.RED)
        Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
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