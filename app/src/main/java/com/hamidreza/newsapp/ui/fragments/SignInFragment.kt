package com.hamidreza.newsapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.hamidreza.newsapp.R
import com.hamidreza.newsapp.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment() : Fragment(R.layout.fragment_sign_in) {
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private var _binding : FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignInBinding.bind(view)
        binding.btnLogIn.setOnClickListener {
            logIn()
        }
    }

    fun logIn(){
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val name = binding.edtName.text.toString().trim()
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()
        when{
            name.isEmpty() -> Toast.makeText(requireContext(), "نام خود را وارد کنید", Toast.LENGTH_SHORT).show()
            email.isEmpty() -> Toast.makeText(requireContext(), "ایمیل خود را وارد کنید", Toast.LENGTH_SHORT).show()
            password.isEmpty() -> Toast.makeText(requireContext(), "رمز عبور خود را وارد کنید", Toast.LENGTH_SHORT).show()
            password.length < 6 -> Toast.makeText(requireContext(), "رمز عبور حداقل 6 کارکتر باشد", Toast.LENGTH_SHORT).show()
            !email.matches(emailPattern.toRegex()) -> Toast.makeText(requireContext(), "ایمیل وارد شده صحیح نمی باشد", Toast.LENGTH_SHORT).show()
        }
        if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty() &&  password.length >= 6 && email.matches(emailPattern.toRegex())){
            findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
            val sh = sharedPreferences.edit()
            sh.putBoolean("IS_LOG_IN",true).apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}