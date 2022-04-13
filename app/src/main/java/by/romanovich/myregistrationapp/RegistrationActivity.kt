package by.romanovich.myregistrationapp

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
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
        binding.cardConteiner.visibility = View.GONE
    }

    //метод что бы достать презентор(объект) сохраненный в lastCustomNonConfigurationInstance если он LoginPresenter то мы его сохраняем
    private fun restorePresenter(): LoginContract.RepositoryPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return presenter
    }
}
