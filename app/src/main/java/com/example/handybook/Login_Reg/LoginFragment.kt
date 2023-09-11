package com.example.handybook.Login_Reg

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.handybook.R
import com.example.handybook.databinding.FragmentLoginBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@SuppressLint("StaticFieldLeak")

class LoginFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentLoginBinding
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
        binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.kirishBtn.setOnClickListener {
            if (binding.emailKirish.text!!.isNotEmpty() && binding.parolKirish.text!!.isNotEmpty() && binding.eslabQolKirish.isChecked) {
                Toast.makeText(requireContext(), "Successfully Login :)", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_boshSahifaFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please, complete all prompts above!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
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