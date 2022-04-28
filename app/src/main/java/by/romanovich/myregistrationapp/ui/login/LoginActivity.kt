package by.romanovich.myregistrationapp.ui.login

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import by.romanovich.myregistrationapp.R
import by.romanovich.myregistrationapp.app
import by.romanovich.myregistrationapp.databinding.ActivityLoginBinding
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.ui.AppState
import by.romanovich.myregistrationapp.ui.forgotPassword.PasswordRecoveryFragment
import by.romanovich.myregistrationapp.ui.registration.RegistrationFragment


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private var viewModel: LoginContract.ViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = restoreViewModel()

        initClickBySubscription()

    }

    private fun initClickBySubscription() {
        viewModel?.getLiveData()?.subscribe(Handler(Looper.getMainLooper())) { state ->
            renderData(state)
        }
        with(binding) {
            loginButton.setOnClickListener {
                viewModel?.onLogin(
                    binding.loginEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
            }
            lostLoginOrPasswordTextView.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.login_container, PasswordRecoveryFragment()).addToBackStack("")
                    .commit()
                Toast.makeText(
                    this@LoginActivity,
                    R.string.lost_your_login_or_password,
                    Toast.LENGTH_SHORT
                ).show()
            }
            createAccTextView.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.login_container, RegistrationFragment()).addToBackStack("")
                    .commit()
                Toast.makeText(this@LoginActivity, R.string.create_account, Toast.LENGTH_SHORT)
                    .show()
            }
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


    //отписываемся
    override fun onDestroy() {
        super.onDestroy()
        viewModel?.getLiveData()?.unsubscribeAll()
    }

    private fun restoreViewModel(): LoginViewModel {
        val viewModel = lastCustomNonConfigurationInstance as? LoginViewModel

        return viewModel ?: LoginViewModel(app.loginUsecase)
    }

    //метод что бы положить объект
    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return viewModel
    }


    private fun showError(error: Exception) {
        binding.cardViewContainer.setBackgroundColor(Color.RED)
        Toast.makeText(this, " " + "$error", Toast.LENGTH_SHORT).show()
    }


    private fun loadAccountData(account: UserProfile) {
        Toast.makeText(this, getString(R.string.succes), Toast.LENGTH_SHORT).show()
    }

    fun setSuccess() {
        binding.cardViewContainer.setBackgroundColor(Color.GREEN)
        Toast.makeText(this, getString(R.string.succes), Toast.LENGTH_SHORT).show()
        hideProgress()
    }

    fun setError(error: String) {
        showProgress()
        binding.cardViewContainer.setBackgroundColor(Color.RED)
        Toast.makeText(this, " " + "$error", Toast.LENGTH_SHORT).show()
    }

    private fun showProgress() {
        binding.cardViewContainer.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.cardViewContainer.visibility = View.VISIBLE
    }
}
