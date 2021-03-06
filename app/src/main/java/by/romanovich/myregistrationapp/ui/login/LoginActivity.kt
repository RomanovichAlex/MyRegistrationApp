package by.romanovich.myregistrationapp.ui.login

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.romanovich.myregistrationapp.R
import by.romanovich.myregistrationapp.app
import by.romanovich.myregistrationapp.databinding.ActivityLoginBinding
import by.romanovich.myregistrationapp.domain.entities.UserProfile
import by.romanovich.myregistrationapp.ui.forgotPassword.PasswordRecoveryFragment
import by.romanovich.myregistrationapp.ui.registration.RegistrationFragment


class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var binding: ActivityLoginBinding
    private var presenter: LoginContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = restorePresenter()
        presenter?.onAttach(this)

        initClick()

    }

    private fun initClick() {
        with(binding) {
            loginButton.setOnClickListener {
                presenter?.onLogin(
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

    //?????????? ?????? ???? ?????????????? ??????????????????(????????????) ?????????????????????? ?? lastCustomNonConfigurationInstance ???????? ???? LoginPresenter ???? ???? ?????? ??????????????????
    private fun restorePresenter(): LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        /*?????? ?????????????????????? ?????? ????????????????, ?????????????????? ?? ?????????? ??????
        val api = (application as App).api*/
        //?????????????????? presenter, ???? ???????? ???? ???????? ???? ?????????????? ?????????? ??????????????????
        return presenter ?: LoginPresenter(app.loginUsecase)
    }

    //?????????? ?????? ???? ???????????????? ????????????
    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }


    override fun setRegister() {
        Toast.makeText(this, getString(R.string.enter_the_data), Toast.LENGTH_SHORT).show()
    }

    override fun loadAccountData(account: UserProfile) {
        Toast.makeText(this, getString(R.string.succes), Toast.LENGTH_SHORT).show()
    }

    override fun setSuccess() {
        binding.cardViewContainer.setBackgroundColor(Color.GREEN)
        Toast.makeText(this, getString(R.string.succes), Toast.LENGTH_SHORT).show()
        hideProgress()
    }

    override fun setError(error: String) {
        showProgress()
        binding.cardViewContainer.setBackgroundColor(Color.RED)
        Toast.makeText(this, " " + "$error", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.cardViewContainer.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.cardViewContainer.visibility = View.VISIBLE
    }
}
