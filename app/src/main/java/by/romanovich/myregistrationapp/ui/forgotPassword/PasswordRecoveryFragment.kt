package by.romanovich.myregistrationapp.ui.forgotPassword

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import by.romanovich.myregistrationapp.R
import by.romanovich.myregistrationapp.app
import by.romanovich.myregistrationapp.databinding.FragmentPasswordRecoveryBinding
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.ui.base.BaseFragment


class PasswordRecoveryFragment :
    BaseFragment<FragmentPasswordRecoveryBinding>(FragmentPasswordRecoveryBinding::inflate),
    PasswordRecoveryContract.PasswordRecoveryViewInterface {

    private var presenter: PasswordRecoveryContract.PasswordRecoveryPresenterInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        presenter = activity?.app?.let { PasswordRecoveryPresenter(it.passwordRecoveryUsecase) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter?.onAttachView(this)

        binding.sendInstructionsAccountButton.setOnClickListener {
            presenter?.findAccount(
                binding.sendInstructionsAccountButton.text.toString()
            )
            Toast.makeText(
                requireContext(),
                getString(R.string.sendInstructions),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun showProgress() {
        binding.cardViewContainer.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.cardViewContainer.visibility = View.VISIBLE
    }

    override fun setSuccess() {
        binding.cardViewContainer.setBackgroundColor(Color.GREEN)
        Toast.makeText(requireContext(), getString(R.string.succes), Toast.LENGTH_SHORT).show()
        hideProgress()
    }

    override fun showError(error: Exception) {
        showProgress()
        binding.cardViewContainer.setBackgroundColor(Color.RED)
        Toast.makeText(requireContext(), " " + "$error", Toast.LENGTH_SHORT).show()
    }

    override fun forgetPasswordData(account: UserProfile) {
        Toast.makeText(requireContext(), getString(R.string.succes), Toast.LENGTH_SHORT).show()
    }
}