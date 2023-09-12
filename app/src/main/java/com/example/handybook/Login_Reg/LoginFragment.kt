package com.example.handybook.Login_Reg

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.handybook.R
import com.example.handybook.databinding.FragmentLoginBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@SuppressLint("StaticFieldLeak")

class LoginFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentLoginBinding
    private var userList = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val shared = requireContext().getSharedPreferences("shared", AppCompatActivity.MODE_PRIVATE)
        val gson = Gson()
        val convert = object : TypeToken<List<User>>() {}.type
        val users = shared.getString("users", "")

        binding = FragmentLoginBinding.inflate(layoutInflater)


        binding.kirishBtn.setOnClickListener {

            if (users != "") {
                userList = gson.fromJson(users, convert)
            }

            for (user in userList) {
                if ((binding.emailKirish.text.toString() == user.username || binding.emailKirish.text.toString() == user.email) && binding.parolKirish.text.toString() == user.password) {
                    Toast.makeText(requireContext(), "Successfully logged in", Toast.LENGTH_SHORT)
                        .show()

                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)

                    shared.edit().putBoolean("isLoggedOut", false).apply()
                    shared.edit().putString("active_user", gson.toJson(user)).apply()

                    return@setOnClickListener
                }
            }
            Toast.makeText(
                requireContext(),
                "Username yoki Parol xato!",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.royhatdanOtishKirish.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}