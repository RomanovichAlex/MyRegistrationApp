package by.romanovich.myregistrationapp.ui.login

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import by.romanovich.myregistrationapp.R
import by.romanovich.myregistrationapp.app
import by.romanovich.myregistrationapp.databinding.ActivityLoginBinding
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.ui.forgotPassword.PasswordRecoveryFragment
import by.romanovich.myregistrationapp.ui.registration.RegistrationFragment
import by.romanovich.myregistrationapp.ui.state.AppState
import by.romanovich.myregistrationapp.ui.state.ViewState


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewSaveState = "ViewSaveState"
    private var viewState: ViewState = ViewState.INIT
    private var viewModel: LoginContract.ViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = restoreViewModel()

        initClickBySubscription()

        restoreStateUi()
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
        when (result) {
            is AppState.Loading -> {
                binding.loginCardViewContainer.isVisible = true
            }
            is AppState.Success -> {
                loadAccountData(result.userProfile)
            }
            is AppState.Error -> {
                showError(result.error)
            }
        }
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
        binding.loginCardViewContainer.setBackgroundColor(Color.RED)
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }


    private fun loadAccountData(account: UserProfile) {
        viewState = ViewState.IS_SUCCESS
        binding.loginCardViewContainer.setBackgroundColor(Color.GREEN)
        Toast.makeText(this, getString(R.string.succes), Toast.LENGTH_SHORT).show()
    }

    private fun restoreStateUi() {
        when (viewState) {
            ViewState.INIT -> {}
            ViewState.ERROR -> {
                binding.loginCardViewContainer.setBackgroundColor(Color.RED)
            }
            ViewState.IS_SUCCESS -> {
                binding.loginCardViewContainer.setBackgroundColor(Color.GREEN)
            }
        }
    }

    //отписываемся
    override fun onDestroy() {
        super.onDestroy()
        viewModel?.getLiveData()?.unsubscribeAll()
    }
}
