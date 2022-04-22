package by.romanovich.myregistrationapp.ui.registration

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import by.romanovich.myregistrationapp.R
import by.romanovich.myregistrationapp.databinding.FragmentRegistrationBinding
import by.romanovich.myregistrationapp.ui.ux.ViewBindingFragment
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout


class RegistrationFragment : ViewBindingFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.createAccountButton.setOnClickListener {
            Toast.makeText(requireContext(), "createAccount", Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = RegistrationFragment()
    }
}