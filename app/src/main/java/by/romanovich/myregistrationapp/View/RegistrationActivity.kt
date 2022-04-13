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

        binding.buttonLogin.setOnClickListener {
            presenter.onLogin(
                binding.editLogin.text.toString(),
                binding.editPassword.text.toString()
            )
        }

        binding.lostLoginOrPassword.setOnClickListener {
            Toast.makeText(this, R.string.lost_your_login_or_password, Toast.LENGTH_SHORT).show()
        }
        binding.createAcc.setOnClickListener {
            Toast.makeText(this, R.string.create_account, Toast.LENGTH_SHORT).show()
        }


    }

    override fun setRegister() {
        Toast.makeText(this, getString(R.string.EnterTheData), Toast.LENGTH_SHORT).show()
    }

    override fun setSuccess() {
        binding.cardConteiner.setBackgroundColor(Color.GREEN)
        Toast.makeText(this, getString(R.string.Succes), Toast.LENGTH_SHORT).show()
        hideProgress()
    }

    override fun setError(error: String){
        showProgress()
        binding.cardConteiner.setBackgroundColor(Color.RED)
        Toast.makeText(this, " " + "$error", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.cardConteiner.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.cardConteiner.visibility = View.VISIBLE
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
