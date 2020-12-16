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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment() : Fragment() {
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_log_in.setOnClickListener {
            logIn()
        }
    }

    fun logIn(){
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val name = edt_name.text.toString().trim()
        val email = edt_email.text.toString().trim()
        val password = edt_password.text.toString().trim()
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
}