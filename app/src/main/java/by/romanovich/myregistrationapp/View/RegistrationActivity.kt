package by.romanovich.myregistrationapp.View

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.romanovich.myregistrationapp.R
import by.romanovich.myregistrationapp.databinding.ActivityRegistrationBinding
import by.romanovich.myregistrationapp.presenter.LoginContract
import by.romanovich.myregistrationapp.presenter.LoginPresenter

class RegistrationActivity : AppCompatActivity(), LoginContract.RepositoryView {
    private lateinit var presenter: LoginContract.RepositoryPresenter
    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = restorePresenter()
        presenter.onAttach(this)

        binding.loginButton.setOnClickListener {
            presenter.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }

        binding.lostLoginOrPassword.setOnClickListener {
            Toast.makeText(this, R.string.lost_your_login_or_password, Toast.LENGTH_SHORT).show()
        }
        binding.createAccTextView.setOnClickListener {
            Toast.makeText(this, R.string.create_account, Toast.LENGTH_SHORT).show()
        }
    }

    override fun setRegister() {
        Toast.makeText(this, getString(R.string.enter_the_data), Toast.LENGTH_SHORT).show()
    }

    override fun setSuccess() {
        binding.cardViewConteiner.setBackgroundColor(Color.GREEN)
        Toast.makeText(this, getString(R.string.succes), Toast.LENGTH_SHORT).show()
        hideProgress()
    }

    override fun setError(error: String){
        showProgress()
        binding.cardViewConteiner.setBackgroundColor(Color.RED)
        Toast.makeText(this, " " + "$error", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.cardViewConteiner.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.cardViewConteiner.visibility = View.VISIBLE
    }

    //метод что бы достать презентор(объект) сохраненный в lastCustomNonConfigurationInstance если он LoginPresenter то мы его сохраняем
    private fun restorePresenter(): LoginContract.RepositoryPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        //возращаем presenter, но если он ноль то создаем новый презентер
        return presenter ?: LoginPresenter()
    }

    //метод что бы положить объект
    override fun onRetainCustomNonConfigurationInstance(): Any {
        return presenter
    }
}
